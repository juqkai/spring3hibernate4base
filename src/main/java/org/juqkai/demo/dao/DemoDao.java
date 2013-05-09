package org.juqkai.demo.dao;

import org.juqkai.demo.model.Demo;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-4
 * Time: 上午10:51
 */
public interface DemoDao {
    Demo fetchDemo(Long id);

    void save(Demo demo);
}
