package org.juqkai.demo.service.demo;

import org.juqkai.demo.model.Demo;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-4
 * Time: 上午10:49
 */
public interface DemoService {

    public Demo fetchDemo(Long id);

    void save(Demo demo);
}
