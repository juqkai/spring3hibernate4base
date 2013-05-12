package org.juqkai.demo.support.dao;

import org.juqkai.demo.support.Part.Part;

import java.io.Serializable;
import java.util.List;

public interface ICommonDao {
    
    public <T> T save(T model);

    public <T> void saveOrUpdate(T model);
    
    public <T> void update(T model);
    
    public <T> void merge(T model);

    public <T, PK extends Serializable> void delete(Class<T> entityClass, PK id);

    public <T> void deleteObject(T model);

    public <T, PK extends Serializable> T get(Class<T> entityClass, PK id);
    
    public <T> int countAll(Class<T> entityClass);
    
    public <T> Part<T> listAll(Class<T> entityClass);
    
    public <T> Part<T> listAll(Class<T> entityClass, Part<T> part);
    
//    public <T> Part<T> listAll(Class<T> entityClass, int pn, int pageSize);
    

}
