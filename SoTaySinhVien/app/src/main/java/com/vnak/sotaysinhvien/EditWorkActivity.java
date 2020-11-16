package com.vnak.sotaysinhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vnak.sotaysinhvien.adapter.AdapterWorkCT;
import com.vnak.sotaysinhvien.model.CalendarSubject;
import com.vnak.sotaysinhvien.model.Work;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditWorkActivity extends AppCompatActivity {

    CalendarSubject work;
    ArrayList<CalendarSubject> listWork;
    Calendar timer = Calendar.getInstance();
    SimpleDateFormat spf = new SimpleDateFormat("HH:mm");
    public static TextInputLayout txtDiaChi;
    public static TextView txtGioBD,txtGioKT;
    Button btnSuaLich;
    public static Spinner thu;
    String[] dsThu;
    ArrayAdapter<String> adapterThu;
    RecyclerView listWord;
    AdapterWorkCT workCT;
    public static int positionWork = 0;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_work);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);

        addControls();
        addEvents();
    }

    private void addEvents() {
        txtGioKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThoiGian(txtGioKT);
            }
        });
        txtGioBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThoiGian(txtGioBD);
            }
        });

        btnSuaLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validete(txtDiaChi)){
                    return;
                }
                xuLySuaLich();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_work,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(EditWorkActivity.this);
                builder.setMessage("Bạn Có Muốn Xóa "+work.getMonhoc()+ " không ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listWork.remove(position);
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();

                break;
            case R.id.editTille:
                AlertDialog.Builder build = new AlertDialog.Builder(EditWorkActivity.this);
                build.setTitle("Chỉnh Sửa Tiêu Đề");
                TextInputLayout inputLayout = new TextInputLayout(this,null,TextInputLayout.BOX_BACKGROUND_OUTLINE);
                final TextInputEditText input = new TextInputEditText (inputLayout.getContext());
                input.setSingleLine();
                inputLayout.setHint("Chủ Đề Mới");
                LinearLayout linearLayout = new LinearLayout(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                inputLayout.setLayoutParams(lp);
                inputLayout.addView(input);
                linearLayout.addView(inputLayout);
                linearLayout.setPadding(50,20,50,0);
                build.setView(linearLayout);
                build.setCancelable(false);
                build.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = input.getText().toString().trim();
                        if (!title.equals("")) {
                            String x = title.substring(0,1).toUpperCase()+ title.substring(1);
                            listWork.get(position).setMonhoc(x);
                            EditWorkActivity.this.setTitle(x);
                            Toast.makeText(EditWorkActivity.this,"Sửa chủ đề thành công",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                build.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                build.create().show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Gson gson = new Gson();
        SharedPreferences preferences = getSharedPreferences("Work", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String json = gson.toJson(listWork);
        System.out.println("WORK " + json);
        editor.putString("work",json);
        editor.commit();
    }

    private void xuLySuaLich() {
        String date = (!thu.getSelectedItem().equals("Chủ Nhật")) ? "T"+((String) thu.getSelectedItem()).substring(4) : "CN";
        String diaChi = txtDiaChi.getEditText().getText().toString().trim();
        String thoiGian = txtGioBD.getText().toString()+"->"+txtGioKT.getText().toString();

        if (checkCalendarLike(date,diaChi,thoiGian)) return;
        CalendarSubject CV = listWork.get(position);
        Work w = CV.getDsWork().get(positionWork);
        w.setThoiGian(date);
        w.setGio(thoiGian);
        w.setDiaDiem(diaChi);
        workCT.notifyDataSetChanged();
        txtGioBD.setText("");
        txtGioKT.setText("");
        txtDiaChi.getEditText().setText("");
        thu.setSelection(0);
        Toast.makeText(getApplicationContext(), "Sửa lịch thành công", Toast.LENGTH_SHORT).show();
    }

    public boolean checkCalendarLike(String date,String diaChi,String time) {

        for (CalendarSubject cv : listWork) {
            int i = 0;
            for (Work w : cv.getDsWork()) {
                if (cv.getMonhoc().equals(work.getMonhoc())) {
                    if (i != positionWork) {
                        if (w.getDiaDiem().equals(diaChi)
                                && w.getGio().equals(time) && w.getThoiGian().equals(date)
                                || w.getGio().equals(time) && w.getThoiGian().equals(date)) {
                            Toast.makeText(getApplicationContext(), "Lịch Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    }
                } else {
                    if (w.getDiaDiem().equals(diaChi)
                            && w.getGio().equals(time) && w.getThoiGian().equals(date)
                            || w.getGio().equals(time) && w.getThoiGian().equals(date)) {
                        Toast.makeText(getApplicationContext(), "Lịch Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                i++;
            }
        }
        return false;
    }

    private boolean validete(TextInputLayout textInputLayout) {
        boolean check = true ;
        String text = textInputLayout.getEditText().getText().toString();
        if (text.isEmpty()) {
            textInputLayout.setError(textInputLayout.getHint()+" không thể để trống");
            return false;
        }
        textInputLayout.setError("");
        return check;
    }

    private void addControls() {
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtGioBD = findViewById(R.id.txtGioBD);
        txtGioKT = findViewById(R.id.txtGioKT);
        btnSuaLich = findViewById(R.id.btnSuaLich);
        thu = findViewById(R.id.spnThu);
        listWord = findViewById(R.id.listWord);
        dsThu = getResources().getStringArray(R.array.Date);
        adapterThu = new ArrayAdapter<>(EditWorkActivity.this,android.R.layout.simple_list_item_1,dsThu);
        thu.setAdapter(adapterThu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        position = getIntent().getIntExtra("Position",0);
        SharedPreferences preferences = getSharedPreferences("Work", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("work",null);

        listWork = gson.fromJson(json, new TypeToken<ArrayList<CalendarSubject>>(){}.getType());
        work = listWork.get(position);
        setTitle(work.getMonhoc());
        if (work.getDsWork().size() > 0) {
            Work w = work.getDsWork().get(0);
            txtDiaChi.getEditText().setText(w.getDiaDiem());
            int positionThu = (!w.getThoiGian().equals("CN")) ? Integer.parseInt(w.getThoiGian().substring(1))-2 : 6;
            thu.setSelection(positionThu);
            String bd = w.getGio().substring(0,5);
            String kt = w.getGio().substring(7);
            txtGioKT.setText(kt);
            txtGioBD.setText(bd);
        }

        workCT = new AdapterWorkCT(EditWorkActivity.this,listWork.get(position).getDsWork());
        listWord.setLayoutManager(new LinearLayoutManager(EditWorkActivity.this));
        listWord.setAdapter(workCT);
    }

    private void xuLyThoiGian(final TextView textView) {
        try {
            if (textView.getText().toString().length() > 0) {
                timer.setTime(spf.parse(textView.getText().toString()));
            }
            TimePickerDialog.OnTimeSetListener callBack = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    timer.set(Calendar.HOUR_OF_DAY,hourOfDay);
                    timer.set(Calendar.MINUTE,minute);
                    timer.set(Calendar.SECOND,0);
                    textView.setText(spf.format(timer.getTime()));
                }
            };
            TimePickerDialog time = new TimePickerDialog(
                    EditWorkActivity.this,
                    callBack,
                    timer.get(Calendar.HOUR_OF_DAY),
                    timer.get(Calendar.MINUTE),
                    true
            );
            time.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}