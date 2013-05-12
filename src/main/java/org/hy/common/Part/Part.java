package org.hy.common.Part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 数据片段,
 * 其实它就是一个page, 只是有别于page,
 * User: juqkai(juqkai@gmail.com)
 * Date: 13-5-11
 * Time: 下午6:37
 */
public class Part<T> implements Collection<T>{
    private List<T> data = new ArrayList<T>();

    //片段长度
    private Integer length = 20;
    //索引, 每几页, 默认为1
    private Integer index = 1;
    //总页数
    private Integer count;
    //总数
    private Integer total;


    public Integer getStartIndex(){
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


    //--------------------------------以下为Collection方法---------------------------------------
    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return data.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return data.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return data.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return data.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return data.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return data.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return data.retainAll(c);
    }

    @Override
    public void clear() {
        data.clear();
    }
}
