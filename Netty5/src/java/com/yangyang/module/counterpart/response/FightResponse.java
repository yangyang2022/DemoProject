package com.yangyang.module.counterpart.response;

import com.yangyang.serial.Serializer;

/**
 */
public class FightResponse extends Serializer{

    //after fight,you will get the number of gold
    private int gold;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    protected void read() {
        this.gold = readInt();
    }

    @Override
    protected void write() {
        writeInt(gold);
    }
}
