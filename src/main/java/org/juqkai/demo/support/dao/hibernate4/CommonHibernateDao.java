package org.juqkai.demo.support.dao.hibernate4;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.juqkai.demo.support.Part.Part;
import org.juqkai.demo.support.dao.ICommonDao;
import org.juqkai.demo.support.log.Log;
import org.juqkai.demo.support.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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
    public <T> Part<T> listAll(Class<T> entityClass) {
        Criteria criteria = getSession().createCriteria(entityClass);
        Part<T> part = new Part<T>();
        part.addAll(criteria.list());
        return part;
    }
    
    public <T> Part<T> listAll(Class<T> entityClass, Part<T> part) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setFirstResult(part.getStart());
        part.addAll(criteria.list());
//        return listAll(entityClass, part, Constants.DEFAULT_PAGE_SIZE);
        return part;
    }
    
//    @SuppressWarnings("unchecked")
//    public <T> Part<T> listAll(Class<T> entityClass, int pn, int pageSize) {
//        Criteria criteria = getSession().createCriteria(entityClass);
//        criteria.setFirstResult(PageUtil.getPageStart(pn, pageSize));
//        return criteria.list();
//
//    }
    

    
}
