package com.yangyang.model;

/**
 * request object
 *
 * data structure | package head | module number | module cmd | data
 *
 * package head is always a static number
 *
 *
 */
public class Request {

    //request module
    private short module;

    //request command
    private short cmd;

    //request datas
    private byte[] datas;

    public Request() {
    }

    public Request(short module, short cmd, byte[] datas) {
        this.module = module;
        this.cmd = cmd;
        this.datas = datas;
    }

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public byte[] getDatas() {
        return datas;
    }

    public void setDatas(byte[] datas) {
        this.datas = datas;
    }
    public int getDataLength(){
        if (datas == null) {
            return  0;
        }
        return datas.length;
    }
}
