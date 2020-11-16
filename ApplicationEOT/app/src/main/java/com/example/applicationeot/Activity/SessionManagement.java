package com.example.applicationeot.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.applicationeot.Models.AccountMG;
import com.example.applicationeot.Models.GioHang;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    String SHARE_PREF_NAME = "SESSION";
    String SESSION_KEY = "SESSION_USER";
    String SESSION_AC = "SESSION_ACCOUNT";
    String SESSION_CART = "SESSION_CART";
    private ArrayList<GioHang> sectionList;

    public SessionManagement(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void SaveSession(AccountMG accountMG){
        String user = accountMG.getName();
        String account = accountMG.getTaikhoan();
        editor.putString(SESSION_KEY, user).commit();
        editor.putString(SESSION_AC, account).commit();
    }



}
