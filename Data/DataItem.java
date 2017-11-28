package com.cavendersoftworks.persistentstoragepractice.Data;

import java.util.UUID;

public class DataItem {
    private String name;
    private String desc;
    private UUID uID;

    public DataItem(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.uID = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUID() {
        return uID.toString();
    }
}
