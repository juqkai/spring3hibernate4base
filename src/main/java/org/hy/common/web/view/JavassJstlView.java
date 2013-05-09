package org.hy.common.web.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.JstlView;

public class JavassJstlView extends JstlView {
    
    @Override
    protected void exposeHelpers(HttpServletRequest request) throws Exception {
        super.exposeHelpers(request);
    }
    
}
