package com.yangyang.HQL;

public class StudentDto {
    private int sid;
    private String sname;
    private String ssex;
    private String sclz;
    private String sspe;

    public StudentDto() {

    }

    public StudentDto(int sid, String sname, String ssex, String sclz, String sspe) {
        this.sid = sid;
        this.sname = sname;
        this.ssex = ssex;
        this.sclz = sclz;
        this.sspe = sspe;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSclz() {
        return sclz;
    }

    public void setSclz(String sclz) {
        this.sclz = sclz;
    }

    public String getSspe() {
        return sspe;
    }

    public void setSspe(String sspe) {
        this.sspe = sspe;
    }
}
