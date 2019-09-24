package com.example.android.continuity;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpThread extends Thread {
    private ServerSocket serverSocket;
    private Socket socket;

    private static boolean isStart=true;
    TcpThread() throws IOException {
        serverSocket = new ServerSocket(3333);
        socket = null;
    }

    @Override
    public void run() {
        super.run();
        while (isStart){
            try {
                socket = serverSocket.accept();
                Thread newClient = new EchoThread(socket);
                newClient.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class EchoThread extends Thread {
        private Socket mSocket;
        protected boolean nb_open;
        DataInputStream in = null;
        DataOutputStream out = null;
        public EchoThread(Socket clientSocket) {
            this.mSocket = clientSocket;
            this.nb_open = true;
        }

        @Override
        public void run() {
            super.run();
            int res;
            if (mSocket.isConnected()){
                try {
                    in = new DataInputStream(mSocket.getInputStream());
                    out = new DataOutputStream(mSocket.getOutputStream());
                    res = in.readInt();
                    if (res==1){
                        Log.v("Echo thread TCP","Camera Event started ");
                    }
                    else{
                        Log.v("Echo thread TCP","Other event started ");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
