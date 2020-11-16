package com.example.appsv;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.adapters.StudentsAdapter;
import com.example.models.Students;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;

    ListView lvtab1;
    ArrayList<Students> dstab1;
    StudentsAdapter adaptertab1;

    ListView lvtab2;
    ArrayList<Students> dstab2;
    StudentsAdapter adaptertab2;

    String DATABASE_NAME = "arrstudents.db";
    String DB_PATH_SUFFIX = "C:/Users/Admin/Desktop/ADR/DBSql";
    SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xulysaochepCSDLvaohethongMobile();

        addControls();
        addEvents();

        Tab1();
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")){
                    Tab1();
                }else if (tabId.equalsIgnoreCase("t2")){
                    Tab2();
                }
            }
        });
    }

    private void Tab2() {

    }

    private void Tab1() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("ListStudents", null, null, null, null, null, null);
        dstab1.clear();
        while (cursor.moveToNext()){
            String mabh = cursor.getString(0);
            String ho = cursor.getString(1);
            String ten = cursor.getString(2);
            String sex = cursor.getString(3);
            String bir = cursor.getString(4);
            String phone = cursor.getString(5);
            int avatar = cursor.getInt(6);
            String addr = cursor.getString(7);
            String des = cursor.getString(8);

            Students students = new Students();
            students.setMa(mabh);
            students.setHo(ho);
            students.setName(ten);
            students.setGender(sex);
            students.setBirthday(bir);
            students.setPhone(phone);
            students.setAvatar(avatar);
            students.setAddress(addr);
            students.setDescription(des);
            dstab1.add(students);
        }
        cursor.close();
        adaptertab1.notifyDataSetChanged();
    }

    private void addControls() {
        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 =tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Tab1");
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 =tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Tab2");
        tabHost.addTab(tab2);

        lvtab1 = findViewById(R.id.lvtab1);
        dstab1 = new ArrayList<>();
        adaptertab1 = new StudentsAdapter(MainActivity.this, R.layout.item, dstab1);
        lvtab1.setAdapter(adaptertab1);

        lvtab2 = findViewById(R.id.lvtab2);
        dstab2 = new ArrayList<>();
        adaptertab2 = new StudentsAdapter(MainActivity.this, R.layout.item, dstab2);
        lvtab2.setAdapter(adaptertab2);

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
