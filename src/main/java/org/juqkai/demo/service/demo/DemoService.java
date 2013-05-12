package org.juqkai.demo.service.demo;

import org.juqkai.demo.model.Demo;
import org.juqkai.demo.support.Part.Part;
import org.juqkai.demo.support.service.IBaseService;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-4
 * Time: 上午10:49
 */
public interface DemoService extends IBaseService<Demo, Long>{

    public Demo fetchDemo(Long id);

    public Part<Demo> findAll();

//    void save(Demo demo);
}
