package com.cavendersoftworks.persistentstoragepractice;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONHelper {

    public static String exportToJSON(ArrayList<DataItem> dataItems) {
        DataItems da = new DataItems();
        da.dataItems = dataItems;

        Gson gson  = new Gson();
        String jsonString = gson.toJson(da);
        Log.i("JSONHelper", "Export to JSON: " + jsonString);
        return jsonString;
    }

    public static ArrayList<DataItem> importFromJSON(Context context) {
        FileReader reader = null;

        try {
            File file = new File(Environment.getExternalStorageDirectory(), MainActivity.FILENAME);
            reader = new FileReader(file);
            Gson gson = new Gson();
            DataItems da  = gson.fromJson(reader, DataItems.class);
            return da.getDataItems();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    static class DataItems {
        ArrayList<DataItem> dataItems;

        public ArrayList<DataItem> getDataItems() {
            return dataItems;
        }

        public void setDataItems(ArrayList<DataItem> dataItems) {
            this.dataItems = dataItems;
        }
    }

}
