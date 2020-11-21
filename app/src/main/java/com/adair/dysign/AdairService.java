package com.adair.dysign;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.adair.dysign.ServerManager;

import java.net.InetAddress;
import android.util.Log;

import java.net.UnknownHostException;

public class AdairService extends Service {
    private static final String TAG = "Adair";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: MyService");
        new Thread() {
            @Override
            public void run() {
                super.run();
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getByName("0.0.0.0");
                    Log.d(TAG, "onCreate: " + inetAddress.getHostAddress());
                    ServerManager serverManager = new ServerManager(getApplicationContext(), inetAddress, 8006);
                    serverManager.startServer();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}