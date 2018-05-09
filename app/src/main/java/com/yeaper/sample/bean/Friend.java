package com.yeaper.sample.bean;

/**
 * Created by yeape on 2018/5/8.
 */

public class Friend {

    private String name;
    private int gender;
    private String describe;

    public Friend(String name, int gender, String describe) {
        this.name = name;
        this.gender = gender;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
