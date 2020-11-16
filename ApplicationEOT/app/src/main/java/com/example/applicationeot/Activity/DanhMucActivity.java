package com.example.applicationeot.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.applicationeot.Adapter.DanhMucAdapter;
import com.example.applicationeot.Models.DanhMucMG;
import com.example.applicationeot.R;
import com.example.applicationeot.Task.TaskDanhMuc;

import java.util.ArrayList;

public class DanhMucActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerdanhmuc;
    private DanhMucAdapter danhMucAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        addControls();
        addEvents();
        DanhMucShow danhMucShow = new DanhMucShow();
        danhMucShow.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menucart:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        toolbar = findViewById(R.id.toolbardm);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerdanhmuc = findViewById(R.id.recyclerdanhmuc);
    }

    class DanhMucShow extends TaskDanhMuc{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<DanhMucMG> danhMucMGS) {
            if (danhMucMGS!=null){
                danhMucAdapter = new DanhMucAdapter(DanhMucActivity.this, danhMucMGS);
                recyclerdanhmuc.setAdapter(danhMucAdapter);
                recyclerdanhmuc.setHasFixedSize(true);
                recyclerdanhmuc.setLayoutManager(new GridLayoutManager(DanhMucActivity.this, 2));
            }
            super.onPostExecute(danhMucMGS);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
