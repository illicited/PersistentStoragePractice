package com.cavendersoftworks.persistentstoragepractice.Data;


import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface DataItemDAO {

    @Query ("SELECT * FROM DataItemSQL")
    List<DataItemDAO> getAll();

    @Query ("SELECT * FROM DataItemSQL WHERE uid IN (:userIds)")
    List<DataItemDAO> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(DataItemDAO... dataItems);

    @Delete
    void delete(DataItemDAO dataItemDAO);

}
