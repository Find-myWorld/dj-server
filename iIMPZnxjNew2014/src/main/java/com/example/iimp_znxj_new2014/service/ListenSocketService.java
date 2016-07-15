package com.example.iimp_znxj_new2014.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.iimp_znxj_new2014.entity.UserInfo;
import com.example.iimp_znxj_new2014.netty.NettyServer;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by yang on 2016/7/6.
 */
public class ListenSocketService extends Service {
    @Override
    public void onCreate() {
        Log.d("tag","服务打开");
        /*开启一个线程，不断地监听端口*/
        new Thread() {
            @Override
            public void run() {
                Log.d("tag","扫描线程开启");
                try {
                    NettyServer.openChannel();//开启一个服务端channel
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
