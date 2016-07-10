package com.example.user.popularmovies;

import android.app.Application;

public class MyApplication extends Application {
    private MainActivity mMainActivity;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public void setMainActivity(MainActivity mCurrentActivity) {
        this.mMainActivity = mCurrentActivity;
    }
}
