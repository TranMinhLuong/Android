package com.example.studyasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText txtsobt;
    private TextView txtload;
    private Button btnsobt;
    private ProgressBar mBar;
    private LinearLayout layoutbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        txtsobt = findViewById(R.id.txtsobt);
        btnsobt = findViewById(R.id.btnsobt);
        mBar = findViewById(R.id.progressBar);
        layoutbt = findViewById(R.id.layoutbt);
        txtload = findViewById(R.id.txtload);
        txtload.setText("0");
    }

    private void addEvents() {
        btnsobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulythoigianthuc();
            }
        });
    }

    private void xulythoigianthuc() {
        int n = Integer.parseInt(txtsobt.getText().toString());
        ButtonTask task = new ButtonTask();
        task.execute(n);
    }

    class ButtonTask extends AsyncTask <Integer, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layoutbt.removeAllViews();
            mBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mBar.setProgress(100);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int value = values[1];
            int percent = values[0];

            mBar.setProgress(percent);
            txtload.setText(percent+"%");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            Button btn = new Button(MainActivity.this);
            btn.setLayoutParams(layoutParams);
            btn.setText(value+"");

            layoutbt.addView(btn);
        }

        @Override
        protected Void doInBackground(Integer... params) {
            int n = params[0];
            Random random = new Random();
            for (int i = 0; i<=n ; i++){
                int percent = i*100/n;
                int value = random.nextInt(500);
                publishProgress(percent,value);
                SystemClock.sleep(100);
            }
            return null;
        }
    }
}
