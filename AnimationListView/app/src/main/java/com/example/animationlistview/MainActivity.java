package com.example.animationlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvten;
    ArrayList<String> dsten;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        lvten = findViewById(R.id.lv);
        dsten = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dsten);
        lvten.setAdapter(adapter);

        for (int i = 1; i<=5000; i++){
            dsten.add("Tên số "+i);
        }
        adapter.notifyDataSetChanged();
    }
}
