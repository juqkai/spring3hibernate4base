package org.juqkai.demo.support.service.impl;

import org.juqkai.demo.support.Part.Part;
import org.juqkai.demo.support.dao.IBaseDao;
import org.juqkai.demo.support.service.IBaseService;

import java.util.List;

public abstract class BaseService<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseService<M, PK> {
    
//    protected IBaseDao<M, PK> baseDao;
    
    public abstract IBaseDao<M, PK> getBaseDao();
    

    @Override
    public M save(M model) {
        getBaseDao().save(model);
        return model;
    }
    
    @Override
    public void merge(M model) {
        getBaseDao().merge(model);
    }

    @Override
    public void saveOrUpdate(M model) {
        getBaseDao().saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        getBaseDao().update(model);
    }
    
    @Override
    public void delete(PK id) {
        getBaseDao().delete(id);
    }

    @Override
    public void deleteObject(M model) {
        getBaseDao().deleteObject(model);
    }

    @Override
    public M get(PK id) {
        return getBaseDao().get(id);
    }

   
    
    @Override
    public int countAll() {
        return getBaseDao().countAll();
    }

    @Override
    public List<M> listAll() {
        return getBaseDao().listAll().getVals();
    }

    @Override
    public Part<M> listAll(Part<M> part) {
        Integer count = countAll();
        return getBaseDao().listAll(part);
    }
    public Part<M> listAllWithOptimize(Part<M> part) {
        Integer count = countAll();
        return getBaseDao().listAll(part);
    }
    
    @Override
    public Part<M> pre(PK pk, Part<M> part) {
        Integer count = countAll();
        return getBaseDao().pre(pk, part);
    }
    @Override
    public Part<M> next(PK pk, Part<M> part) {
        Integer count = countAll();
        return getBaseDao().next(pk, part);
    }

}
