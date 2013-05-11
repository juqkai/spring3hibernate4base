package org.hy.common.service;

import java.io.Serializable;
import java.util.List;

import org.hy.common.pagination.Page;

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

    public <T> Page<T> listAll(Class<T> entityClass, int pn);

    public <T> Page<T> listAll(Class<T> entityClass, int pn, int pageSize);

}
