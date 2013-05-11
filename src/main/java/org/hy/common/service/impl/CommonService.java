package org.hy.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hy.common.Constants;
import org.hy.common.dao.ICommonDao;
import org.hy.common.pagination.Page;
import org.hy.common.pagination.PageUtil;
import org.hy.common.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
        return commonDao.listAll(entityClass);
    }
    
    public <T> Page<T> listAll(Class<T> entityClass, int pn) {
        return listAll(entityClass, pn, Constants.DEFAULT_PAGE_SIZE);
    }
    
    public <T> Page<T> listAll(Class<T> entityClass, int pn, int pageSize) {
        int total = countAll(entityClass);
        List<T> items = commonDao.listAll(entityClass, pn, pageSize);
        return PageUtil.getPage(total, pn, items, pageSize);
    }

}