package org.juqkai.demo.dao;

import org.juqkai.demo.model.Demo;
import org.juqkai.demo.support.Part.Part;
import org.juqkai.demo.support.dao.IBaseDao;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-4
 * Time: 上午10:51
 */
public interface DemoDao extends IBaseDao<Demo, Long>{
    Demo fetchDemo(Long id);

    Part<Demo> findAll();
}
