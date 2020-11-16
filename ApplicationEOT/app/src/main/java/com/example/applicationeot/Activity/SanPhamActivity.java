package com.example.applicationeot.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.applicationeot.Adapter.SanPhamAdapter;
import com.example.applicationeot.Models.SanPhamMG;
import com.example.applicationeot.R;
import com.example.applicationeot.Task.TaskSanPham;

import java.util.ArrayList;

public class SanPhamActivity extends AppCompatActivity {
    private RecyclerView recyclerdmsp;
    private Toolbar toolbardmsp;
    private SanPhamAdapter sanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);

        addControls();
        addEvents();
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

    @Override
    protected void onStart() {
        Intent intent = getIntent();
        String iddm = intent.getStringExtra("danhmucsp");
        System.out.println(iddm);
        SanPhamDMShow sanPhamDMShow = new SanPhamDMShow();
        sanPhamDMShow.execute(iddm);
        super.onStart();
    }

    private void addEvents() {
        toolbardmsp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        recyclerdmsp = findViewById(R.id.recyclersp);
        toolbardmsp = findViewById(R.id.toolbarsp);
        setSupportActionBar(toolbardmsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    class SanPhamDMShow extends TaskSanPham{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<SanPhamMG> sanPhamMGS) {
            sanPhamAdapter = new SanPhamAdapter(getApplicationContext(), sanPhamMGS);
            recyclerdmsp.setAdapter(sanPhamAdapter);
            recyclerdmsp.setHasFixedSize(true);
            recyclerdmsp.setLayoutManager(new LinearLayoutManager(SanPhamActivity.this));
            super.onPostExecute(sanPhamMGS);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
