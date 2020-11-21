package com.adair.dysign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.adair.dysign.util.NetUtil;
import com.ss.sys.ces.a;

import java.net.InetAddress;
import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Adair";
    private int version = Build.VERSION.SDK_INT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text_view);

        try {
            InetAddress inetAddress = NetUtil.getLocalIPAddress();
            if (inetAddress != null){
                textView.setText(inetAddress.getHostAddress() + "  version: " + version);
            }else {
                textView.setText("未获取到inetAddress" + "  version: " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void operate(View view) {
        switch (view.getId()){
            case R.id.id_bt_index:
                //启动服务:创建-->启动-->销毁
                //如果服务已经创建了，后续重复启动，操作的都是同一个服务，不会再重新创建了，除非你先销毁它
                Intent it1 = new Intent(this, AdairService.class);
                Log.d(TAG, "operate: button");
                startService(it1);
                ((Button) view).setText("服务已开启");
                break;
        }
    }

    public static String Gorgon(int currentTimeMillis, String url){
        String format_url = format_url(url);
        String str3 = "00000000000000000000000000000000";
        String a2 = md5(format_url);
        String str4 = "00000000000000000000000000000000";
        String str5 = "00000000000000000000000000000000";
        byte[] data = format_str(a2 + str3 + str4 + str5);
        byte[] sign = a.leviathan(currentTimeMillis, data);
        String gorgon = getGorgon(sign);
        return gorgon;
    }

    public static String format_url(String str){
        int indexOf = str.indexOf("?");
        int indexOf2 = str.indexOf("#");
        if (indexOf == -1) {
            return null;
        }
        if (indexOf2 == -1) {
            return str.substring(indexOf + 1);
        }
        if (indexOf2 < indexOf) {
            return null;
        }
        return str.substring(indexOf + 1, indexOf2);
    }

    private static String a(byte[] bArr, int i) {
        char[] f66652a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        } else if (i + 0 <= bArr.length) {
            int i2 = i * 2;
            char[] cArr = new char[i2];
            int i3 = 0;
            for (int i4 = 0; i4 < i; i4++) {
                int b2 = bArr[i4 + 0] & 255;
                int i5 = i3 + 1;
                cArr[i3] = f66652a[b2 >> 4];
                i3 = i5 + 1;
                cArr[i5] = f66652a[b2 & 15];
            }
            return new String(cArr, 0, i2);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public static String md5(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(str.getBytes("UTF-8"));
                    byte[] digest = instance.digest();
                    return a(digest, digest.length);
                }
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static byte[] format_str(String str) {
        String str2 = str;
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str2.charAt(i), 16) << 4) + Character.digit(str2.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String getGorgon(byte[] bArr) {
        byte[] bArr2 = bArr;
        char[] charArray = "0123456789abcdef".toCharArray();
        char[] cArr = new char[(bArr2.length * 2)];
        for (int i = 0; i < bArr2.length; i++) {
            int b2 = bArr2[i] & 255;
            int i2 = i * 2;
            cArr[i2] = charArray[b2 >>> 4];
            cArr[i2 + 1] = charArray[b2 & 15];
        }
        return new String(cArr);
    }

}