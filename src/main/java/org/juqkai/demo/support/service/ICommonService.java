package org.juqkai.demo.support.service;

import org.juqkai.demo.support.part.Part;

import java.io.Serializable;
import java.util.List;

public interface ICommonService {

    public <T> T save(T model);

    public <T> void saveOrUpdate(T model);

    public <T> void update(T model);

    public <T> void merge(T model);

    public <T, PK extends Serializable> void delete(Class<T> entityClass, PK id);

    public <T> void deleteObject(T model);

    public <T, PK extends Serializable> T get(Class<T> entityClass, PK id);

    public <T> int countAll(Class<T> entityClass);

    public <T> List<T> listAll(Class<T> entityClass);

    public <T> Part<T> listAll(Class<T> entityClass, Part<T> part);

}
