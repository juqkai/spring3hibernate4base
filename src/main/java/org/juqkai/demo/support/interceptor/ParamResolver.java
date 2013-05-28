package org.juqkai.demo.support.interceptor;

import org.juqkai.demo.support.part.Part;
import org.juqkai.demo.support.util.Strings;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-28
 * Time: 下午1:05
 */
//@Component
public class ParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Part.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Object clazz = methodParameter.getParameterType();
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        if (Part.class.equals(clazz)) {
            Part part = new Part();
            String index = request.getParameter(Part.PARAM_INDEX);
            String length = request.getParameter(Part.PARAM_LENGTH);
            part.setIndex(Strings.isBlank(index) ? 1 : Integer.parseInt(index));
            part.setLength(Strings.isBlank(length) ? 1 : Integer.parseInt(length));
            String url = request.getRequestURI();
            String param = request.getQueryString();
            param = param.replaceAll("&" + Part.PARAM_INDEX + "=\\d*", "");
            param = param.replaceAll("&" + Part.PARAM_LENGTH + "=\\d*", "");
            param = param.replaceAll("\\?" + Part.PARAM_INDEX + "=\\d*&", "");
            param = param.replaceAll("\\?" + Part.PARAM_LENGTH + "=\\d*&", "");
            param = Strings.isBlank(param) ? "" : "?" + param;
            part.setUrl(url + param);
            return part;
        }
        return null;
    }
}
