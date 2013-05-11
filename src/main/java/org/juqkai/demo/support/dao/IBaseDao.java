package org.juqkai.demo.support.dao;

import org.juqkai.demo.support.Part.Part;

import java.util.List;

public interface IBaseDao<M extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public PK save(M model);

    public void saveOrUpdate(M model);
    
    public void update(M model);
    
    public void merge(M model);

    public void delete(PK id);

    public void deleteObject(M model);

    public M get(PK id);
    
    public int countAll();

    public Part<M> listAll();

    public Part<M> listAll(Part<M> part);
    
    public Part<M> pre(PK pk, Part<M> part);
    public Part<M> next(PK pk, Part<M> part);
    
    boolean exists(PK id);
    
    public void flush();
    
    public void clear();

}
