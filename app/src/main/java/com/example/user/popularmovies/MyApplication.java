package com.example.user.popularmovies;

import android.app.Application;

import com.example.user.popularmovies.helpers.Contextor;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }
}
