package com.example.giuaki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.giuaki.Adapter.MonAnAdapter;
import com.example.giuaki.Models.MonAn;

import java.util.ArrayList;

public class CacMonAnActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<MonAn> dsmonan;
    private MonAnAdapter anAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cac_mon_an);

        addControls();
        addEvents();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem, menu);
        return super.onCreateOptionsMenu(menu);
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
        toolbar = findViewById(R.id.toolbarmonan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recymonan);
        dsmonan = new ArrayList<>();
        dsmonan.add(new MonAn(R.drawable.mn1, "MÓN ĂN TRUYỀN THÔNG VIỆT NAM"));
        dsmonan.add(new MonAn(R.drawable.mn2, "MÓN NGON DỄ LÀM"));
        dsmonan.add(new MonAn(R.drawable.mn3, "MÓN KEM NỔI TIẾNG"));
        dsmonan.add(new MonAn(R.drawable.mn5, "MÓN BÁNH NỔI TIẾNG"));
        anAdapter = new MonAnAdapter(CacMonAnActivity.this, dsmonan);
        recyclerView.setAdapter(anAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CacMonAnActivity.this));
    }
}
