package org.juqkai.demo.support.tag;

import org.juqkai.demo.support.part.Part;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-28
 * Time: 上午10:43
 */
public class PartTag extends TagSupport {
    private Part part;

    private String page = "<div class=\"pagination\">\n" +
            "              <ul>\n" +
            "                <li><a href=\"%s\">«</a></li>\n" +
            "                %s" +
            "                <li><a href=\"%s\">»</a></li>\n" +
            "             </ul>\n" +
            "            </div>";
    private String item = "<li><a href=\"%s\">%s</a></li>";

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest req = (HttpServletRequest) this.pageContext.getRequest();
        HttpServletResponse resp = (HttpServletResponse) this.pageContext.getResponse();
        String page = makePage();
        try {
            this.pageContext.getOut().write(page);
//            resp.getWriter().write(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Tag.EVAL_BODY_INCLUDE;
    }

    private String makePage() {
        String items = "";
        int start = part.getIndex() > 2 ? part.getIndex() - 2 : 1;
        int end = part.getCount() - part.getIndex() > 2 ? part.getIndex() + 2 : part.getCount();
        for (int i = start; i <= end; i++) {
            items += String.format(item, fetchUrl(i, Part.DEFAULT_LENGTH), i);
        }
        return String.format(page, fetchUrl(1, Part.DEFAULT_LENGTH), items, fetchUrl(part.getCount(), Part.DEFAULT_LENGTH));
    }
    /**
     * 生成连接
     * @param index
     * @param length
     * @return
     */
    private String fetchUrl(Integer index, Integer length) {
        String url = part.getUrl();
        String path = "&" + Part.PARAM_INDEX + "=" + index;
        path += "&" + Part.PARAM_LENGTH + "=" + length;
        if (url.indexOf('?') < 0) {
            url += "?";
        }
        url += path;
        return url;
    }


    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
