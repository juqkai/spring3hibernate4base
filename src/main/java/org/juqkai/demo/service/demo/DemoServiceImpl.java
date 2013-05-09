package org.juqkai.demo.service.demo;

import org.juqkai.demo.dao.DemoDao;
import org.juqkai.demo.model.Demo;
import org.juqkai.demo.support.log.Log;
import org.juqkai.demo.support.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-4
 * Time: 上午10:49
 */
@Service
public class DemoServiceImpl implements DemoService {
    private static final Log log = Logs.get();

    @Autowired
    private DemoDao demoDao;

    public Demo fetchDemo(Long id) {
        return demoDao.fetchDemo(id);
    }

    public void save(Demo demo){
        demoDao.save(demo);
    }

}
