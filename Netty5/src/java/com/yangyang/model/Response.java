package com.yangyang.model;

/**
 *  response object
 *
 */
public class Response {

    //request module
    private short module;

    //request command
    private short cmd;

    //request datas
    private byte[] datas;

    //states code
    private int stateCode;

    public Response() {
    }

    public Response(short module, short cmd,int stateCode, byte[] datas) {
        this.module = module;
        this.cmd = cmd;
        this.datas = datas;
        this.stateCode = stateCode;
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

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }
    public int getDataLength(){
        if (datas == null) {
            return  0;
        }
        return datas.length;
    }
}
