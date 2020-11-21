package com.ss.sys.ces;

import android.content.Context;
import android.util.Log;


public class a {
    static {
            try{
                Log.i("adairLoadSo", "start load so");
                System.loadLibrary("cms");
                Log.i("adairLoadSo", "load so success");
            } catch (UnsatisfiedLinkError unused) {
            }
    }


    public static native byte[] d(byte[] bArr);

    public static native byte[] e(byte[] bArr);

    public static native void jni(int i, int i2);

    public static native void jns(int i, String str);

    public static native byte[] leviathan(int i, byte[] bArr);

    public static native byte[] rb(Context context, String str, String str2);

    public static native void ws(int i);

    public final void world() {
    }
}
