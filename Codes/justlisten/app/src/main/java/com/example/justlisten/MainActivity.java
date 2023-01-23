package com.example.justlisten;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter("com.mobisec.intent.action.FLAG_ANNOUNCEMENT");
        MyReceiver receiver = new MyReceiver();
        registerReceiver(receiver, intentFilter);


    }

}

