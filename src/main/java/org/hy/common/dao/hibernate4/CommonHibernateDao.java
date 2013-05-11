package org.hy.common.dao.hibernate4;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hy.common.Constants;
import org.hy.common.dao.ICommonDao;
import org.hy.common.pagination.PageUtil;
import org.juqkai.demo.support.log.Log;
import org.juqkai.demo.support.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("CommonHibernateDao")
public class CommonHibernateDao implements ICommonDao {
    protected static final Log LOG = Logs.get();
  
      @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    } 
    
    
    public <T> T save(T model) {
        getSession().save(model);
        return model;
    }

    public <T> void saveOrUpdate(T model) {
        getSession().saveOrUpdate(model);
        
    }
    
    public <T> void update(T model) {
        getSession().update(model);
    }
    
    public <T> void merge(T model) {
        getSession().merge(model);
    }

    public <T, PK extends Serializable> void delete(Class<T> entityClass, PK id) {
        getSession().delete(get(entityClass, id));
    }

    public <T> void deleteObject(T model) {
        getSession().delete(model);
    }

    public <T, PK extends Serializable> T get(Class<T> entityClass, PK id) {
        return (T) getSession().get(entityClass, id);
        
    }
    
    public <T> int countAll(Class<T> entityClass) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    
    @SuppressWarnings("unchecked")
    public <T> List<T> listAll(Class<T> entityClass) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setProjection(Projections.rowCount());
        return criteria.list();
    }
    
    public <T> List<T> listAll(Class<T> entityClass, int pn) {
        return listAll(entityClass, pn, Constants.DEFAULT_PAGE_SIZE);
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> listAll(Class<T> entityClass, int pn, int pageSize) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setFirstResult(PageUtil.getPageStart(pn, pageSize));
        return criteria.list();
        
    }
    

    
}
