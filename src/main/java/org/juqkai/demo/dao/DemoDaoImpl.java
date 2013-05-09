package org.juqkai.demo.dao;

import org.hibernate.SessionFactory;
import org.juqkai.demo.model.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-4
 * Time: 上午11:37
 */
@Repository
public class DemoDaoImpl implements DemoDao{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    @Override
    public Demo fetchDemo(Long id) {
        return (Demo) sessionFactory.getCurrentSession().get(Demo.class, id);
    }

    @Override
    public void save(Demo demo) {
        sessionFactory.getCurrentSession().save(demo);
    }
}
