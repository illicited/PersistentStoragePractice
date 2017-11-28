package com.cavendersoftworks.persistentstoragepractice.Data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.UUID;

@Entity
public class DataItemSQL {

    @PrimaryKey
    private UUID uid;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Description")
    private String desc;

    public DataItemSQL(String name, String desc) {
        this.uid = UUID.randomUUID();
        this.name = name;
        this.desc = desc;
    }

    public String getUid() {
        return uid.toString();
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
}
