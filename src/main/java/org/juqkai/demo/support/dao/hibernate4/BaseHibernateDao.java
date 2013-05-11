package org.juqkai.demo.support.dao.hibernate4;

import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;
import org.juqkai.demo.support.Part.Part;
import org.juqkai.demo.support.dao.IBaseDao;
import org.juqkai.demo.support.dao.util.ConditionQuery;
import org.juqkai.demo.support.dao.util.OrderBy;
import org.juqkai.demo.support.log.Log;
import org.juqkai.demo.support.log.Logs;
import org.juqkai.demo.support.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.Map.Entry;

public abstract class BaseHibernateDao<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseDao<M, PK> {

    protected static final Log LOG = Logs.get();

    private final Class<M> entityClass;
    private final String HQL_LIST_ALL;
    private final String HQL_COUNT_ALL;
    private final String HQL_OPTIMIZE_PRE_LIST_ALL;
    private final String HQL_OPTIMIZE_NEXT_LIST_ALL;
    private String pkName = null;

    @SuppressWarnings("unchecked")
    public BaseHibernateDao() {
        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = this.entityClass.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Id.class)) {
                this.pkName = f.getName();
            }
        }

        Assert.notNull(pkName);
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " desc";
        HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " > ? order by " + pkName + " asc";
        HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " < ? order by " + pkName + " desc";
        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
    }

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession() {
        //事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }


    @SuppressWarnings("unchecked")
    @Override
    public PK save(M model) {
        return (PK) getSession().save(model);
    }

    @Override
    public void saveOrUpdate(M model) {
        getSession().saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        getSession().update(model);

    }

    @Override
    public void merge(M model) {
        getSession().merge(model);
    }

    @Override
    public void delete(PK id) {
        getSession().delete(this.get(id));

    }

    @Override
    public void deleteObject(M model) {
        getSession().delete(model);

    }

    @Override
    public boolean exists(PK id) {
        return get(id) != null;
    }

    @Override
    public M get(PK id) {
        return (M) getSession().get(this.entityClass, id);
    }

    @Override
    public int countAll() {
        Long total = aggregate(HQL_COUNT_ALL);
        return total.intValue();
    }


    @Override
    public Part<M> listAll() {
        return listAll(HQL_LIST_ALL);
    }

    @Override
    public Part<M> listAll(Part<M> part) {
        return list(HQL_LIST_ALL, part);
    }

    @Override
    public Part<M> pre(PK pk, Part<M> part) {
        if (pk == null) {
            return list(HQL_LIST_ALL, part);
        }
        //倒序，重排
        Part<M> result = list(HQL_OPTIMIZE_PRE_LIST_ALL, part, pk);
        Collections.reverse(result);
        return result;
    }

    @Override
    public Part<M> next(PK pk, Part<M> part) {
        if (pk == null) {
            return list(HQL_LIST_ALL, part);
        }
        return list(HQL_OPTIMIZE_NEXT_LIST_ALL, part, pk);
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    protected long getIdResult(String hql, Object... paramlist) {
        long result = -1;
        List<?> list = listAll(hql, paramlist);
        if (list != null && list.size() > 0) {
            return ((Number) list.get(0)).longValue();
        }
        return result;
    }

    protected List<M> listSelf(final String hql, final Part<M> part, final Object... paramlist) {
        return this.<M>list(hql, part, paramlist);
    }


    /**
     * for in
     */
    @SuppressWarnings("unchecked")
    protected Part<M> listWithIn(final String hql, final Part<M> part, final Map<String, Collection<?>> map, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        Part<M> p = part;
        setParameters(query, paramlist);
        if (map != null) {
            for (Entry<String, Collection<?>> e : map.entrySet()) {
                query.setParameterList(e.getKey(), e.getValue());
            }
        }
        if (null != p) {
            p.setTotal(listCount(query.getQueryString(), p, paramlist));
            query.setFirstResult(p.getStart());
            query.setMaxResults(p.getLength());
        } else {
            p = new Part<M>();
            p.setTotal(listCount(query.getQueryString(), p, paramlist));
        }
        List<M> results = query.list();
        p.addAll(results);
        return p;
    }

    /**
     * 查询
     *
     * @param hql
     * @param paramlist 参数列表
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Part<M> list(final String hql, final Part<M> part, final Object... paramlist) {
        return listWithIn(hql, part, null, paramlist);
    }

    protected Integer listCount(final String hql, final Part<M> part, final Object... paramlist) {
        LOG.info(hql);
        String countHql = hql.substring(hql.toLowerCase().indexOf("from"));
        countHql = "select count(*) " + countHql;
        LOG.info(countHql);
        Query query = getSession().createQuery(countHql);
        return Integer.parseInt(query.uniqueResult().toString());
    }

    /**
     * 根据查询条件返回唯一一条记录
     */
    @SuppressWarnings("unchecked")
    protected <T> T unique(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        return (T) query.setMaxResults(1).uniqueResult();
    }

    /**
     * for in
     */
    @SuppressWarnings("unchecked")
    protected <T> T aggregate(final String hql, final Map<String, Collection<?>> map, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        if (paramlist != null) {
            setParameters(query, paramlist);
            for (Entry<String, Collection<?>> e : map.entrySet()) {
                query.setParameterList(e.getKey(), e.getValue());
            }
        }

        return (T) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    protected <T> T aggregate(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);

        return (T) query.uniqueResult();

    }


    /**
     * 执行批处理语句.如 之间insert, update, delete 等.
     */
    protected int execteBulk(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }

    protected int execteNativeBulk(final String natvieSQL, final Object... paramlist) {
        Query query = getSession().createSQLQuery(natvieSQL);
        setParameters(query, paramlist);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }

    protected Part<M> listAll(final String sql, final Object... paramlist) {
        return list(sql, new Part<M>(), paramlist);
    }

    @SuppressWarnings("unchecked")
    public Part<M> listByNative(final String nativeSQL, final Part<M> part,
                                    final List<Entry<String, Class<?>>> entityList,
                                    final List<Entry<String, Type>> scalarList, final Object... paramlist) {

        SQLQuery query = getSession().createSQLQuery(nativeSQL);
        Part<M> p = part;
        if (entityList != null) {
            for (Entry<String, Class<?>> entity : entityList) {
                query.addEntity(entity.getKey(), entity.getValue());
            }
        }
        if (scalarList != null) {
            for (Entry<String, Type> entity : scalarList) {
                query.addScalar(entity.getKey(), entity.getValue());
            }
        }

        setParameters(query, paramlist);
        if (null != p) {
            query.setMaxResults(p.getLength());
            query.setFirstResult(p.getStart());
        } else {
            p = new Part<M>();
        }
        List<M> result = query.list();
        p.addAll(result);
        return p;
    }

    @SuppressWarnings("unchecked")
    protected <T> T aggregateByNative(final String natvieSQL, final List<Entry<String, Type>> scalarList, final Object... paramlist) {
        SQLQuery query = getSession().createSQLQuery(natvieSQL);
        if (scalarList != null) {
            for (Entry<String, Type> entity : scalarList) {
                query.addScalar(entity.getKey(), entity.getValue());
            }
        }

        setParameters(query, paramlist);

        Object result = query.uniqueResult();
        return (T) result;
    }

    @SuppressWarnings("unchecked")
    public Part<M> list(ConditionQuery query, OrderBy orderBy, final Part<M> part) {
        Criteria criteria = getSession().createCriteria(this.entityClass);
        Part<M> p = new Part<M>();
        query.build(criteria);
        orderBy.build(criteria);
        if (null != p) {
            criteria.setFirstResult(p.getStart());
            criteria.setMaxResults(p.getLength());
        } else {
            p = new Part<M>();
        }
        List<M> list = criteria.list();
        p.addAll(list);
        return p;
    }

    @SuppressWarnings("unchecked")
    public Part<M> list(Criteria criteria) {
        Part<M> part = new Part<M>();
        part.addAll(criteria.list());
        return part;
    }

    @SuppressWarnings("unchecked")
    public <T> T unique(Criteria criteria) {
        return (T) criteria.uniqueResult();
    }

    public Part<M> list(DetachedCriteria criteria) {
        return list(criteria.getExecutableCriteria(getSession()));
    }

    @SuppressWarnings("unchecked")
    public <T> T unique(DetachedCriteria criteria) {
        return (T) unique(criteria.getExecutableCriteria(getSession()));
    }

    protected void setParameters(Query query, Object[] paramlist) {
        if (paramlist != null) {
            for (int i = 0; i < paramlist.length; i++) {
                if (paramlist[i] instanceof Date) {
                    //TODO 难道这是bug 使用setParameter不行？？
                    query.setTimestamp(i, (Date) paramlist[i]);
                } else {
                    query.setParameter(i, paramlist[i]);
                }
            }
        }
    }


}
