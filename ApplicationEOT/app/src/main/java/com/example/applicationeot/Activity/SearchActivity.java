package com.example.applicationeot.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.applicationeot.Adapter.SanPhamAdapter;
import com.example.applicationeot.Models.SanPhamMG;
import com.example.applicationeot.R;
import com.example.applicationeot.Task.TaskSearch;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    SanPhamAdapter adapterSearch;
    RecyclerView recyclerView;
    ProgressBar progressBarsubj;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        addControls();
        addEvents();
    }

    private void addControls() {
        recyclerView = findViewById(R.id.recycler1);
        progressBarsubj =  findViewById(R.id.progressBar);
        editText = findViewById(R.id.edittext1);
    }

    private void addEvents() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String a = editText.getText().toString();
                GetHttpRespone getHttpRespone = new GetHttpRespone();
                getHttpRespone.execute(a);
            }
        });
    }

    class GetHttpRespone extends TaskSearch {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<SanPhamMG> sanPhamMGS) {
            super.onPostExecute(sanPhamMGS);
            adapterSearch = new SanPhamAdapter(getApplicationContext(), sanPhamMGS);
            recyclerView.setAdapter(adapterSearch);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}