package com.yangyang.model;

import java.util.ArrayList;
import java.util.List;

public class Pager<E> {

    private int pageSize;
    private int pageIndex;
    private int totalRecord;
    private int totalPage;
    private int pageOffset;

    public Pager() {
        datas = new ArrayList<E>();
    }

    public Pager(int pageSize, int pageIndex, int totalRecord, int totalPage) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        datas = new ArrayList<E>();
    }

    public List<E> getDatas() {
        return datas;
    }

    public void setDatas(List<E> datas) {
        this.datas = datas;
    }

    private List<E> datas;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
