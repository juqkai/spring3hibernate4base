package org.juqkai.demo.support.util;

import com.surekam.gjb2.model.Code;

import java.util.ArrayList;
import java.util.List;

/**
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-24
 * Time: 下午5:05
 */
public class Params {
    private String[] name;
    private Long[] operation;
    private String[] val;
    private List<Param> paramList;

    public List<Param> parse() {
        if (null != paramList) {
            return paramList;
        }
        if (null == name || null == operation || null == val) {
            return new ArrayList<Param>();
        }
        if (name.length != operation.length || operation.length != val.length) {
            Assert.state(false, "参数,条件,值不匹配!");
        }
        int size = name.length;
        paramList = new ArrayList<Param>();
        for (int i = 0; i < size; i++) {
            Param param = new Param();
            param.setName(name[i]);
            param.setOperation(new Code(operation[i]));
            param.setVal(val[i]);
            paramList.add(param);
        }
        return paramList;
    }


    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public Long[] getOperation() {
        return operation;
    }

    public void setOperation(Long[] operation) {
        this.operation = operation;
    }

    public String[] getVal() {
        return val;
    }

    public void setVal(String[] val) {
        this.val = val;
    }
}
