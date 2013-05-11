package org.juqkai.demo.support.service;

import java.util.List;

import org.hy.common.pagination.Page;
import org.juqkai.demo.support.Part.Part;

public interface IBaseService<M extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public M save(M model);

    public void saveOrUpdate(M model);
    
    public void update(M model);
    
    public void merge(M model);

    public void delete(PK id);

    public void deleteObject(M model);

    public M get(PK id);
    
    public int countAll();
    
    public List<M> listAll();
    
    public Part<M> listAll(int pn);
    
    public Part<M> listAll(int pn, int pageSize);
    

    public Part<M> pre(PK pk, int pn, int pageSize);
    
    public Part<M> next(PK pk, int pn, int pageSize);
    
    public Part<M> pre(PK pk, int pn);
    
    public Part<M> next(PK pk, int pn);
}
