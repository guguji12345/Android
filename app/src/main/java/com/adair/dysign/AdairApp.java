package com.adair.dysign;

import android.app.Application;

import androidx.annotation.NonNull;

public class AdairApp extends Application {

    private static AdairApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null) {
            mInstance = this;
        }
    }

    @NonNull
    public static AdairApp getInstance() {
        return mInstance;
    }
}