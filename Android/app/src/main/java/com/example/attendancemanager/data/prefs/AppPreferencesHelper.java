package com.example.attendancemanager.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.attendancemanager.di.scopes.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {


    private SharedPreferences sharedPreferences;

    public static final String PREFS_KEY_TOKEN = "access-token";
    public static final String PREFS_KEY_USER_ID = "user_id";

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,@PreferencesInfo String filename){
        sharedPreferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return sharedPreferences.getString(PREFS_KEY_TOKEN,"");
    }

    @Override
    public void setAccessToken(String token) {
        sharedPreferences.edit().putString(PREFS_KEY_TOKEN,token).apply();
    }

    @Override
    public String getUserId() {
        return sharedPreferences.getString(PREFS_KEY_USER_ID,"Empty Value");
    }
}
