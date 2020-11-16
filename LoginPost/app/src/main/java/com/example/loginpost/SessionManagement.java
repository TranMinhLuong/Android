package com.example.loginpost;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    String SHARE_PREF_NAME = "SESSION";
    String SESSION_KEY = "SESSION_USER";

    public SessionManagement(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(ModelLogin modelLogin){
        String user = modelLogin.getTen();
        editor.putString(SESSION_KEY, user).commit();
    }


}
