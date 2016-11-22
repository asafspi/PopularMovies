package com.test.popularmovies;

import android.app.Application;

import com.test.popularmovies.helpers.Contextor;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }
}
