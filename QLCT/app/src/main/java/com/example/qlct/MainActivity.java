package com.example.qlct;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import com.example.qlct.adapter.AdapterA;
import com.example.qlct.models.ModelsA;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterA adapterA;
    ArrayList<ModelsA> ds;
    Button btnintent;
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EXAMPLE){
            if (resultCode == RESULT_OK){
                ModelsA modelsA = (ModelsA) data.getSerializableExtra("data");
                ModelsA a = new ModelsA();
                a.setTuoi(modelsA.getTuoi());
                a.setTen(modelsA.getTen());
                ds = new ArrayList<>();
                ds.add(a);
                adapterA = new AdapterA(getApplicationContext(), ds);
                recyclerView.setAdapter(adapterA);
                adapterA.notifyDataSetChanged();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        }
    }

    private void addControls() {
        recyclerView = findViewById(R.id.recy);
        btnintent = findViewById(R.id.btnintent);
    }

    private void addEvents() {
        btnintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManHinh2Activity.class);
                startActivityForResult(intent,REQUEST_CODE_EXAMPLE);
            }
        });
    }
}
