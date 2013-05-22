package org.juqkai.demo.dao;

import org.hibernate.SessionFactory;
import org.juqkai.demo.model.Demo;
import org.juqkai.demo.support.part.Part;
import org.juqkai.demo.support.dao.hibernate4.BaseHibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-4
 * Time: 上午11:37
 */
@Repository
public class DemoDaoImpl extends BaseHibernateDao<Demo, Long> implements DemoDao{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    @Override
    public Demo fetchDemo(Long id) {
        return (Demo) sessionFactory.getCurrentSession().get(Demo.class, id);
    }

    public Part<Demo> findAll() {
        String hql = "select id, name from Demo";
        return list(hql, null);
    }

}
