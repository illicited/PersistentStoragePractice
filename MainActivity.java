package com.cavendersoftworks.persistentstoragepractice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "test.txt";
    public static final int REQUEST_WRITE_PERMISSION = 1002;
    private boolean permissionGranted;
    TextView output;
    ArrayList<DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.textView);
        output.setText(R.string.txt_textView);

        dataItems.add(new DataItem("Josh Cavender", "Studliest dude you'll find this side of the Mississippi"));
        dataItems.add(new DataItem("Reid Briley", "Poor Reid will he ever figure the world out?"));
    }

    private void checkWritePermission() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))  != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE_PERMISSION);
        }
    }

    public void onCreateButtonClick(View view) {
        checkWritePermission();
        String result = JSONHelper.exportToJSON(this, dataItems);
        FileOutputStream fos = null;
        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);

        try {
            fos = new FileOutputStream(file);
            fos.write(result.getBytes());
            Toast.makeText(this, "File " + FILENAME + " generated successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error generating file: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDeleteButtonClick(View view) {
        File file = new File(getFilesDir(), FILENAME);

        if(file.exists()) {
            deleteFile(FILENAME);
            Toast.makeText(this, "File " + FILENAME + " deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No file was found", Toast.LENGTH_SHORT).show();
        }

    }

    public void onReadButtonClick(View view) {
        File file = new File(getFilesDir(), FILENAME);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String input;

        if(file.exists()) {
            try {
                fis = openFileInput(FILENAME);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((input = br.readLine()) != null) {
                    sb.append(input).append("\n");
                }
                output.setText(sb.toString());
                Toast.makeText(this, "File read successfully", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                try {
                    fis.close();
                    isr.close();
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
        }
    }
}
