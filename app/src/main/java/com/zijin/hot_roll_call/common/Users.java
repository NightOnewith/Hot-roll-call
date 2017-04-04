package com.zijin.hot_roll_call.common;

/**
 * Created by yin on 2017/4/1.
 */
public class Users {
    private String name;
    private String number;
    private String mac;
    private String time;

    public Users() {
    }

    public Users(String name, String number, String mac, String time) {
        this.name = name;
        this.number = number;
        this.mac = mac;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", mac='" + mac + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
