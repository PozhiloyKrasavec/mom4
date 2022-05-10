package com.example.mom4.Model;

public class Client {
    int id;
    String FIO;
    String platform;
    String address;

    public Client(int id, String FIO, String platform, String address) {
        this.id = id;
        this.FIO = FIO;
        this.platform = platform;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
