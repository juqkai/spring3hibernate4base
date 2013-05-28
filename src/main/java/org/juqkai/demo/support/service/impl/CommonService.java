package org.juqkai.demo.support.service.impl;

import org.juqkai.demo.support.part.Part;
import org.juqkai.demo.support.dao.ICommonDao;
import org.juqkai.demo.support.service.ICommonService;
import org.juqkai.demo.support.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("CommonService")
public class CommonService implements ICommonService {

    @Autowired
    @Qualifier("CommonHibernateDao")
    private ICommonDao commonDao;


    public <T> T save(T model) {
        return commonDao.save(model);
    }

    public <T> void saveOrUpdate(T model) {
        commonDao.saveOrUpdate(model);

    }

    public <T> void update(T model) {
        commonDao.update(model);
    }

    public <T> void merge(T model) {
        commonDao.merge(model);
    }

    public <T, PK extends Serializable> void delete(Class<T> entityClass, PK id) {
        commonDao.delete(entityClass, id);
    }

    public <T> void deleteObject(T model) {
        commonDao.deleteObject(model);
    }

    public <T, PK extends Serializable> T get(Class<T> entityClass, PK id) {
        return commonDao.get(entityClass, id);

    }

    public <T> int countAll(Class<T> entityClass) {
        return commonDao.countAll(entityClass);
    }

    public <T> List<T> listAll(Class<T> entityClass) {
        return commonDao.listAll(entityClass).getVals();
    }

    public <T> Part<T> listAll(Class<T> entityClass, Part<T> part) {
        int total = countAll(entityClass);
        return commonDao.listAll(entityClass, part);
    }

    public <M> Part<M> findForParams(Class<M> entityClass, Params params, Part<?> part) {
        return commonDao.findForParams(entityClass, params, part);
    }

}
