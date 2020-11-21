package com.adair.dysign.util;

import android.util.Log;


public class Logger {

    private static final String TAG = "DYSign";
    private static final boolean DEBUG = true;

    public static void i(Object obj) {
        if (DEBUG) {
            Log.i(TAG, obj == null ? "null" : obj.toString());
        }
    }

    public static void d(Object obj) {
        if (DEBUG) {
            Log.d(TAG, obj == null ? "null" : obj.toString());
        }
    }

    public static void v(Object obj) {
        if (DEBUG) {
            Log.v(TAG, obj == null ? "null" : obj.toString());
        }
    }

    public static void w(Object obj) {
        if (DEBUG) {
            Log.w(TAG, obj == null ? "null" : obj.toString());
        }
    }

    public static void e(Object obj) {
        if (DEBUG) {
            Log.e(TAG, obj == null ? "null" : obj.toString());
        }
    }
}

