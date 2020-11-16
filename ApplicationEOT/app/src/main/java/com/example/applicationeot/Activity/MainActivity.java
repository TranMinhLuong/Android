package com.example.applicationeot.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.applicationeot.Adapter.RandSanPhamAdapter;
import com.example.applicationeot.Models.GioHang;
import com.example.applicationeot.Models.SanPhamMG;
import com.example.applicationeot.R;
import com.example.applicationeot.Task.TaskRandSP;
import com.example.applicationeot.Until.CheckConnection;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerViewtc;
    private RandSanPhamAdapter adapterrandsp;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    public static ArrayList<GioHang> dsgiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (CheckConnection.NetworkConnection(getApplicationContext())) {
            addControls();
            addEvents();
            ActionViewFlipper();
            RandSpShow randSpShow = new RandSpShow();
            randSpShow.execute();
        }else {
            CheckConnection.Toast_Connection(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    private void ActionViewFlipper() {
        ArrayList<String> dsviewfl = new ArrayList<>();
        dsviewfl.add("https://i.pinimg.com/564x/f5/fc/31/f5fc31a78e5e5504e950d069b0d3a108.jpg");
        dsviewfl.add("https://i.pinimg.com/564x/f1/8e/cc/f18eccb1535450740672035ff7d54107.jpg");
        dsviewfl.add("https://i.pinimg.com/564x/d7/41/19/d74119bb8bcaed83d608c31c0b1a6219.jpg");
        dsviewfl.add("https://i.pinimg.com/564x/30/c9/4f/30c94f605db28589d8c6fd5148d9b8af.jpg");
        dsviewfl.add("https://i.pinimg.com/564x/a4/47/c4/a447c42ea707fbf3b9bd5508ac2b5e18.jpg");
        for (int i = 0 ; i<dsviewfl.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(dsviewfl.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slight_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menucart:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void addControls() {
        toolbar = findViewById(R.id.toolbartc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        drawerLayout = findViewById(R.id.dllayout);
        viewFlipper = findViewById(R.id.viewfliptc);
        recyclerViewtc = findViewById(R.id.recyclertc);
        navigationView = findViewById(R.id.nvg);
        navigationView.setNavigationItemSelectedListener(this);
        HeaderNav();
//        SharedPreferences preferences = getSharedPreferences("GioHang", Context.MODE_PRIVATE);
        if (dsgiohang != null) {

        } else {
            dsgiohang =new ArrayList<>();
        }
//        if (preferences != null ) {
//            dsgiohang.clear();
//            Gson gson = new Gson();
//            String json = preferences.getString("GioHang", null);
//            System.out.println("JSON"+ json);
//            if (json != null) {
//                ArrayList<GioHang> list = gson.fromJson(json, new TypeToken<ArrayList<GioHang>>() {
//                }.getType());
//                for (GioHang gh : list) {
//                    dsgiohang.add(gh);
//                }
//            }
//        }
    }

    private void HeaderNav() {
        String SESSION_KEY = "SESSION_USER";
        String SESSION_AC = "SESSION_ACCOUNT";
        View view = navigationView.getHeaderView(0);
        TextView activityusername = view.findViewById(R.id.activityusername);
        TextView activityaccount = view.findViewById(R.id.activity_main_tv_email);
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String name = sessionManagement.sharedPreferences.getString(SESSION_KEY, "");
        String account = sessionManagement.sharedPreferences.getString(SESSION_AC, "");
        activityusername.setText(name);
        activityaccount.setText(account);
        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.logout).setVisible(true);
        nav_Menu.findItem(R.id.dangnhap).setVisible(false);
        if (name == ""){
            nav_Menu.findItem(R.id.logout).setVisible(false);
            nav_Menu.findItem(R.id.dangnhap).setVisible(true);
            activityusername.setText("Android Black");
            activityaccount.setText("androidblack.24dp@gmail.com");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.trangchu:
                if (CheckConnection.NetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnection.Toast_Connection(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case  R.id.danhmuc:
                if (CheckConnection.NetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(getApplicationContext(), DanhMucActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnection.Toast_Connection(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.search:
                if (CheckConnection.NetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnection.Toast_Connection(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.dangnhap:
                if (CheckConnection.NetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnection.Toast_Connection(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.logout:
                if (CheckConnection.NetworkConnection(getApplicationContext())){
                    SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                    sessionManagement.editor.clear();
                    sessionManagement.editor.commit();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    CheckConnection.Toast_Connection(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
        }
        return  true;
    }

    class RandSpShow extends TaskRandSP{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<SanPhamMG> sanPhamMGS) {
            if (sanPhamMGS!=null){
                adapterrandsp = new RandSanPhamAdapter(getApplicationContext(), sanPhamMGS);
                recyclerViewtc.setAdapter(adapterrandsp);
                recyclerViewtc.setHasFixedSize(true);
                recyclerViewtc.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            }
            super.onPostExecute(sanPhamMGS);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
