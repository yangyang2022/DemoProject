package com.yangyang.model;

import java.util.List;

public class Pager<T> {
    private List<T> datas;
    private int pageOffet;
    private int pageSize;
    private long totalRecord;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getPageOffet() {
        return pageOffet;
    }

    public void setPageOffet(int pageOffet) {
        this.pageOffet = pageOffet;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }
}
