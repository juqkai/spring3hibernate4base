package org.juqkai.demo.support.util;

import com.surekam.gjb2.model.Code;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-24
 * Time: 下午5:03
 */
public class Param {
    private String name;
    private Code operation;
    private String val;


    /**
     * 生成条件
     * @return
     */
    public Criterion makeRestrictions(){
        switch (operation.getId().intValue()) {
            case 3001:
                return Restrictions.eq(name, val);
            case 3002:
                return Restrictions.gt(name, val);
            case 3003:
                return Restrictions.lt(name, val);
            case 3004:
                return Restrictions.ne(name, val);
            case 3005:
                return Restrictions.ge(name, val);
            case 3006:
                return Restrictions.le(name, val);
            case 3007:
                return Restrictions.like(name, val, MatchMode.ANYWHERE);
//            case 3008:
//                return Restrictions.(name, val);
//            case 3001:
//                return Restrictions.eq(name, val);
        }
        Assert.state(false, "不支持的操作符!");
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Code getOperation() {
        return operation;
    }

    public void setOperation(Code operation) {
        this.operation = operation;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
