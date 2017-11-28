package com.cavendersoftworks.persistentstoragepractice.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DataItemDAO.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract DataItemDAO dataItemDAO();

}
