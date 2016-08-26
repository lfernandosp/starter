package com.fernandopereira.starterapp;

import android.app.Application;

import com.fernandopereira.starterapp.inject.AppDependencyInjector;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppDependencyInjector.init();
    }
}
