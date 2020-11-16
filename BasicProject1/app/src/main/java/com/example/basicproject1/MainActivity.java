package com.example.basicproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.adapter.MusicAdapter;
import com.example.models.Music;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;

    ListView lvBHG;
    ArrayList<Music> dsBHG;
    MusicAdapter arrayAdaptermusic;

    ListView lvBYT;
    ArrayList<Music> dsBHYT;
    MusicAdapter arrayAdapterlove;


    public static String DATABASE_NAME = "listmusic.db";
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xulysaochepCSDLvaohethongMobile();

        addControls();
        addEvents();

        Original();
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")){
                   Original();
                }else if(tabId.equalsIgnoreCase("t2")){
                    Love();
                }
            }

        });
    }

    private void Love() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("ListMusic", null, "YEUTHICH=?", new String[]{"1"}, null, null, null);
        dsBHYT.clear();
        while (cursor.moveToNext()){
            String mabh = cursor.getString(0);
            String tenbh = cursor.getString(1);
            String casi = cursor.getString(2);
            int thich = cursor.getInt(4);

            Music music = new Music();
            music.setIdsong(mabh);
            music.setNamesong(tenbh);
            music.setSingger(casi);
            music.setLike(thich == 1);

            dsBHYT.add(music);
        }
        cursor.close();
        arrayAdapterlove.notifyDataSetChanged();
    }

    private void Original() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("ListMusic", null, null, null, null, null, null);
        dsBHG.clear();
        while (cursor.moveToNext()){
            String mabh = cursor.getString(0);
            String tenbh = cursor.getString(1);
            String casi = cursor.getString(2);
            int thich = cursor.getInt(4);

            Music music = new Music();
            music.setIdsong(mabh);
            music.setNamesong(tenbh);
            music.setSingger(casi);
            music.setLike(thich == 1);

            dsBHG.add(music);
        }
        cursor.close();
        arrayAdaptermusic.notifyDataSetChanged();
    }

    private void addControls() {

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.music));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.lovem));
        tabHost.addTab(tab2);


        lvBHG = findViewById(R.id.lvBaihatgoc);
        dsBHG = new ArrayList<>();
        arrayAdaptermusic = new MusicAdapter(MainActivity.this, R.layout.item, dsBHG);
        lvBHG.setAdapter(arrayAdaptermusic);

        lvBYT = findViewById(R.id.lvBaiyeuthich);
        dsBHYT = new ArrayList<>();
        arrayAdapterlove = new MusicAdapter(MainActivity.this, R.layout.item, dsBHYT);
        lvBYT.setAdapter(arrayAdapterlove);

        //giaLapBH();
    }

    private void giaLapBH() {
        dsBHG.add(new Music("1111","Buồn của anh", "Đạt G", true));
        dsBHG.add(new Music("2222","Có như không có", "Hiền Hồ", true));
        dsBHG.add(new Music("3333","Cần một lí do", "K-ICM", false));
        dsBHG.add(new Music("4444","Sau tất cả", "Erik", true));
        arrayAdaptermusic.notifyDataSetChanged();
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
