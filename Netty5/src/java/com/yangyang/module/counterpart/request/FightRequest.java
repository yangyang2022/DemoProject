package com.yangyang.module.counterpart.request;

import com.yangyang.serial.Serializer;

public class FightRequest extends Serializer{


    //counterpart id
    private int counterpartId;

    //fight number
    private int count;

    public FightRequest() {
    }

    public FightRequest(int counterpartId, int count) {
        this.counterpartId = counterpartId;
        this.count = count;
    }

    public int getCounterpartId() {
        return counterpartId;
    }

    public void setCounterpartId(int counterpartId) {
        this.counterpartId = counterpartId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected void read() {
        this.counterpartId = readInt();
        this.count = readInt();
    }

    @Override
    protected void write() {
        writeInt(counterpartId);
        writeInt(count);
    }
}
