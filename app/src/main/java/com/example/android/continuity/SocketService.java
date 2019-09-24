package com.example.android.continuity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.example.android.continuity.ChangeIpActivity.port;


public class SocketService extends Service {
    private Socket socket;
    private ServerSocket serverSocket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private TcpThread tcpThread;

    SocketService(){
        socket = null;
        serverSocket = null;
        dataInputStream = null;
        dataOutputStream = null;
        tcpThread = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        Toast.makeText(this,"Service Started",Toast.LENGTH_SHORT).show();
        super.onStartCommand(intent,flags,startID);
        try {
            tcpThread = new TcpThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tcpThread.start();
        return START_STICKY;
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
        tcpThread.interrupt();
        Toast.makeText(this,"Service Destroyed",Toast.LENGTH_SHORT).show();

    }
}
