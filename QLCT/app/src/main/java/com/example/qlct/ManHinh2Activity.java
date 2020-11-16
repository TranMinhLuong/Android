
package com.example.qlct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.qlct.models.ModelsA;

import java.util.ArrayList;

public class ManHinh2Activity extends AppCompatActivity {
    EditText edt1, edt2;
    Button btnset;
    ArrayList<ModelsA> ds;
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh2);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelsA modelsA = new ModelsA();
                modelsA.setTen(String.valueOf(edt1.getText()));
                modelsA.setTuoi(String.valueOf(edt2.getText()));
                ds = new ArrayList<>();
                ds.add(modelsA);
                Intent intent = new Intent();
                intent.putExtra("data", modelsA);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }



    private void addControls() {
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btnset =  findViewById(R.id.btnset);
    }
}
