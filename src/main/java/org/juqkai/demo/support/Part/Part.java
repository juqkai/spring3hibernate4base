package org.juqkai.demo.support.Part;

import java.util.*;

/**
 * 数据片段,
 * 其实它就是一个page, 只是有别于page,
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-11
 * Time: 下午6:37
 */
public class Part<T> extends ArrayList<T>{
    //片段长度
    private Integer length = 20;
    //索引, 每几页, 默认为1
    private Integer index = 1;
    //总页数
    private Integer count;
    //总数
    private Integer total;


    public Integer getStart(){
        return index - 1 * length;
    }

    /**
     * 是否第一页
     * @return
     */
    public Boolean isFirst() {
        return index <= 1;
    }

    /**
     * 是否最后一页
     * @return
     */
    public Boolean isEnd(){
        return index >= count;
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        if (total % length > 0) {
            this.count = total / length + 1;
        } else {
            this.count = total /length;
        }
    }
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        if (index <= 0) {
            index = 1;
        }
        if (index > count) {
            index = count;
        }
        this.index = index;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}