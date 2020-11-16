package com.example.recycleviewfacebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.adapter.FacebookAdapter;
import com.example.models.FacebookManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<FacebookManager> dsfb;
    FacebookAdapter adapterfb;

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
        recyclerView = findViewById(R.id.recycler);
        dsfb = new ArrayList<>();
        dsfb.add(new FacebookManager(R.drawable.panda1, "User1", "50minutes", "Đẹp zai không :) !", R.drawable.panda1));
        dsfb.add(new FacebookManager(R.drawable.panda2, "User2", "55minutes", "Đẹp zai không :) !", R.drawable.panda2));
        dsfb.add(new FacebookManager(R.drawable.panda3, "User3", "40minutes", "Đẹp zai không :) !", R.drawable.panda3));

        adapterfb = new FacebookAdapter(MainActivity.this, dsfb);
        recyclerView.setAdapter(adapterfb);

        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        RecyclerView.LayoutManager layoutManager = manager;
        recyclerView.setLayoutManager(layoutManager);
    }
}
