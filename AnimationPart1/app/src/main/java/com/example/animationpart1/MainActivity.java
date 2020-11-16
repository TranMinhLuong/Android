package com.example.animationpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnxoaycontrol, btnxoaymh, btn3s, btnlist;

    Animation animation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addControls();
        addEvents();
    }

    private void addControls() {
        btnxoaycontrol = findViewById(R.id.btnxoaycontrol);
        btnxoaymh = findViewById(R.id.btnxoaymh);
        btn3s = findViewById(R.id.bnt3s);
        btnlist = findViewById(R.id.btnlist);

        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.xoaycontrol);
    }

    private void addEvents() {
        btnxoaycontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnxoaycontrol.startAnimation(animation);
            }
        });
        btnxoaymh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutmanhinh = findViewById(R.id.layoutmanhinh);
                animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.xoaymanhinh);
                layoutmanhinh.startAnimation(animation);
            }
        });
        btn3s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.xoaynguoc3s);
                btn3s.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }
}
