package com.example.android.continuity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class ChangeIpActivity extends AppCompatActivity {
    public static String IpAddr;
    public static int port=0;
    public static EditText changePort;
    public static TextView IpDetail;
    private boolean flag1, flag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ip);
        IpDetail = (TextView) findViewById(R.id.ipAddr);
        changePort = (EditText) findViewById(R.id.changePort);
        Button saveButton = (Button) findViewById(R.id.saveIP);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                port = Integer.parseInt(changePort.getText().toString());
                finish();
            }
        });
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        IpAddr = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        IpDetail.setText("IP Address : "+IpAddr);
    }

}