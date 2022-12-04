package com.example.justask;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// com.mobisec.justask
// com.mobisec.intent.action.JUSTASKBUTNOTSOSIMPLE
// com.mobisec.intent.action.JUSTASK

/*
There is an app installed on the system. The app has four activities.
Each of them has one part of the flag.
If you ask them nicely, they will all kindly reply with their part of the flag.
They will reply with an Intent, the part of the flag is somehow contained there.
Check the app's manifest for the specs.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.mobisec.justask", "com.mobisec.justask.PartOne"));
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                Log.i("MOBISEC", data.getStringExtra("flag"));

            }
        }
    }
}