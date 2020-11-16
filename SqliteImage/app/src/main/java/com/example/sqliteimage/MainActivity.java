package com.example.sqliteimage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.DoVatAdapters;
import com.example.database.Databases;
import com.example.models.DoVat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnthem;
    private ListView lvdovat;
    private ArrayList<DoVat> dsdovat;
    private DoVatAdapters adaptersdovat;
    public static Databases databases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
        databases();
        GetData();
    }

    private void GetData() {
        //getData
        Cursor cursor = databases.GetData("SELECT * FROM DoVat");
        dsdovat.clear();
        while (cursor.moveToNext()){
            dsdovat.add(new DoVat(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }
        adaptersdovat.notifyDataSetChanged();
    }

    public void DialogSuaDoVat(String ten, final String mota, final int id){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_suadv);

        final EditText edtten = dialog.findViewById(R.id.edtten);
        final EditText edtmota = dialog.findViewById(R.id.edtmota);
        Button btnxn = dialog.findViewById(R.id.btnxn);
        Button btnkxn = dialog.findViewById(R.id.btnkxn);

        edtten.setText(ten);
        edtmota.setText(mota);

        btnxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi = edtten.getText().toString().trim();
                String motamoi = edtmota.getText().toString().trim();
                databases.QueryData("UPDATE DoVat SET Ten = '"+tenmoi+"', MoTa = '"+motamoi+"' WHERE Id = '"+id+"'");
                Toast.makeText(MainActivity.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetData();
            }
        });

        btnkxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void databases() {
        databases = new Databases(this, "Quanly.sqlite", null, 1);
        databases.QueryData("CREATE TABLE IF NOT EXISTS DoVat(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");
    }



    private void addEvents() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThemDoVatActivity.class));
            }
        });
    }

    private void addControls() {
        btnthem = findViewById(R.id.btnadd);
        lvdovat = findViewById(R.id.lvdovat);
        dsdovat = new ArrayList<>();
        adaptersdovat = new DoVatAdapters(this, R.layout.item_dovat, dsdovat);

        lvdovat.setAdapter(adaptersdovat);
    }
}
