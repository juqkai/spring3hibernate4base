package org.hy.common.service;

import java.io.Serializable;
import java.util.List;

import org.hy.common.model.AbstractModel;
import org.hy.common.pagination.Page;

public interface ICommonService {
    
    public <T extends AbstractModel> T save(T model);

    public <T extends AbstractModel> void saveOrUpdate(T model);
    
    public <T extends AbstractModel> void update(T model);
    
    public <T extends AbstractModel> void merge(T model);

    public <T extends AbstractModel, PK extends Serializable> void delete(Class<T> entityClass, PK id);

    public <T extends AbstractModel> void deleteObject(T model);

    public <T extends AbstractModel, PK extends Serializable> T get(Class<T> entityClass, PK id);
    
    public <T extends AbstractModel> int countAll(Class<T> entityClass);
    
    public <T extends AbstractModel> List<T> listAll(Class<T> entityClass);
    
    public <T extends AbstractModel> Page<T> listAll(Class<T> entityClass, int pn);
    
    public <T extends AbstractModel> Page<T> listAll(Class<T> entityClass, int pn, int pageSize);
    
}
