package com.yangyang.PoiUtil;

public class ExcelHeader  implements Comparable<ExcelHeader>{

    private String title;
    private Integer order;
    private String methodName;

    public ExcelHeader() {
    }

    public ExcelHeader(String title, Integer order, String methodName) {
        this.title = title;
        this.order = order;
        this.methodName = methodName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public int compareTo(ExcelHeader o) {
        return order.compareTo(o.getOrder());
    }
}
