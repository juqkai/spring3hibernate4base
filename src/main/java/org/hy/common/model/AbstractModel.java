package org.hy.common.model;

import org.hy.common.service.ICommonService;
import org.hy.common.util.SpringContextUtil;

public abstract class AbstractModel implements java.io.Serializable {
    


    /**
	 * 
	 */
	private static final long serialVersionUID = 926886392330633823L;

//	@Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }

    
    public void save() {
        ICommonService commonService = SpringContextUtil.getBean("CommonService");
        commonService.save(this);
    }
    
    public void delete() {
        ICommonService commonService = SpringContextUtil.getBean("CommonService");
        commonService.deleteObject(this);
    }
    
    public void update() {
        ICommonService commonService = SpringContextUtil.getBean("CommonService");
        commonService.update(this);
    }
}
