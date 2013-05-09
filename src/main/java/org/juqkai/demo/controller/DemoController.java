package org.juqkai.demo.controller;

import org.juqkai.demo.model.Demo;
import org.juqkai.demo.service.demo.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-6
 * Time: 上午11:33
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView test(@PathVariable("id")Long id) {
        ModelAndView view = new ModelAndView("/demo");
        Demo demo = demoService.fetchDemo(id);
        view.addObject("demo", demo);
        return view;
    }

    @RequestMapping(value = "/save")
    public ModelAndView save(Demo demo) {
        ModelAndView view = new ModelAndView("/demo");
        demoService.save(demo);
        return view;
    }
}
