package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragment.AlbumFragment;
import com.example.fragment.SettingFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
    }

    private void addControls() {
        drawerLayout = findViewById(R.id.dllayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView view = findViewById(R.id.nvg);
        view.setNavigationItemSelectedListener(MainActivity.this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_layout,new AlbumFragment());
        ft.commit();

        view.setCheckedItem(R.id.home);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId())
        {
            case R.id.trangchu:

                break;
            case R.id.caidat:
                ft.replace(R.id.fragment_layout,new SettingFragment());
                ft.commit();
                break;
            case R.id.hinhanh:
                ft.replace(R.id.fragment_layout,new AlbumFragment());
                ft.commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        item.setChecked(true);
        setTitle(item.getTitle());
        return true;
    }

}
