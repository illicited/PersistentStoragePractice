package com.cavendersoftworks.persistentstoragepractice;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class JSONHelper {

    public static String exportToJSON(Context context, ArrayList<DataItem> dataItems) {
        DataItems da = new DataItems();
        da.dataItems = dataItems;

        Gson gson  = new Gson();
        String jsonString = gson.toJson(da);
        Log.i("JSONHelper", "Export to JSON: " + jsonString);
        return jsonString;
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
