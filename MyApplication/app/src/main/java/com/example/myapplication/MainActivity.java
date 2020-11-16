package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.models.Manager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtname, edtsongay;
    Button btnxacnhan;

    Spinner spthu;
    ArrayList<String> dsthu;
    ArrayAdapter<String> adapterthu;
    int lastselected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xulyxacnhan();
            }

        });

        spthu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Bạn Chọn"+dsthu.get(position), Toast.LENGTH_LONG).show();
                lastselected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Xulyxacnhan() {
        if (lastselected == -1){
            Toast.makeText(MainActivity.this, "Bạn chưa chọn thứ mà ???", Toast.LENGTH_LONG).show();
            return;
        }
        Manager nv = new Manager();
        nv.setTennv(edtname.getText().toString());
        nv.setThucongtac(dsthu.get(lastselected));
        nv.setSongayct(Integer.parseInt(edtsongay.getText().toString()));

        Toast.makeText(MainActivity.this, nv.toString(), Toast.LENGTH_LONG).show();
    }

    private void addControls() {
        edtname = (EditText) findViewById(R.id.edtname);
        edtsongay = (EditText) findViewById(R.id.edtsongay);
        btnxacnhan = (Button) findViewById(R.id.btnxacnhan);
        spthu = (Spinner) findViewById(R.id.spthungay);
        dsthu = new ArrayList<>();

        dsthu.add("Thứ 2");dsthu.add("Thứ 3");dsthu.add("Thứ 4");dsthu.add("Thứ 5");
        dsthu.add("Thứ 6");dsthu.add("Thứ 7");dsthu.add("Chủ nhật");

        adapterthu = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, dsthu);
        adapterthu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spthu.setAdapter(adapterthu);
    }
}
