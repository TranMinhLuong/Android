package com.example.testsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String DATABASE_NAME = "test.db";
    String DB_PATH_SUFFIX = "/database/";
    SQLiteDatabase database = null;

    ListView lvstudent;
    ArrayList<String> dsstudent;
    ArrayAdapter<String> adapterstudent;

    Button btnaddstudent;
    Button btnupdate;
    Button btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xulysaochepCSDLvaohethongMobile();

        addControls();
        addEvents();

        showAllStudent();
    }

    private void showAllStudent() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("Students", null, null, null, null, null, null);
        dsstudent.clear();
        while (cursor.moveToNext()){
            int ma = cursor.getInt(0);
            String name = cursor.getString(1);
            int tuoi = cursor.getInt(2);
            dsstudent.add(ma+" - "+name+" - "+tuoi);
            adapterstudent.notifyDataSetChanged();
        }
    }

    private void addEvents() {
        btnaddstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyaddstudents();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudents();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudents();
            }
        });
    }

    private void deleteStudents() {
        database.delete("Students", "ID=?",new String[]{"4"});
        showAllStudent();
    }

    private void updateStudents() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", "Công Sơn");
        database.update("Students", contentValues, "ID=?", new String[]{"4"});
        showAllStudent();
    }

    private void xulyaddstudents() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", 4);
        contentValues.put("Name", "Sơn");
        contentValues.put("Tuoi", 11);
        database.insert("Students", null, contentValues);
        Toast.makeText(MainActivity.this, "Vừa thêm một học sinh", Toast.LENGTH_LONG).show();
        showAllStudent();
    }

    private void addControls() {
        lvstudent = findViewById(R.id.lvstudent);
        dsstudent = new ArrayList<>();
        adapterstudent = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dsstudent);
        lvstudent.setAdapter(adapterstudent);
        btnaddstudent = findViewById(R.id.btnadd);
        btnupdate = findViewById(R.id.btnup);
        btndelete = findViewById(R.id.btndel);
    }

    private void xulysaochepCSDLvaohethongMobile() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (dbFile.exists()){
            try {
                CopyDataFromAsset();
                Toast.makeText(this, "Copy Success", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataFromAsset() {
        try{
            InputStream inputStream = getAssets().open(DATABASE_NAME);
            String outFileName = layduongdanluutru();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0 ,length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (Exception ex){
            Log.e("Lỗi sao chép", ex.toString());
        }
    }

    private String layduongdanluutru(){
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }
}
