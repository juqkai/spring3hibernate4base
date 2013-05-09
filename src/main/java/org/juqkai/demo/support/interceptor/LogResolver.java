package org.juqkai.demo.support.interceptor;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-9
 * Time: 下午10:17
 */

import org.juqkai.demo.support.log.Log;
import org.juqkai.demo.support.log.Logs;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 *
 * @author juqkai(juqkai@gmail.com)
 */
@Component
public class LogResolver implements HandlerExceptionResolver {
    Log log = Logs.get();

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error(ex.getMessage(), ex);
        return null;
    }

}
