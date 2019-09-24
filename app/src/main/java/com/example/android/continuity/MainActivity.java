package com.example.android.continuity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button mStartButton = null;
    public Button mStopButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartButton = (Button) findViewById(R.id.start);
        mStopButton = (Button) findViewById(R.id.stop);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(v.getContext(),SocketService.class));
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(v.getContext(),SocketService.class));
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ip:
                changeIp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void changeIp() {
        Intent changeIpIntent = new Intent(MainActivity.this,ChangeIpActivity.class);
        startActivity(changeIpIntent);
    }
}

