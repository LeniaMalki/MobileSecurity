package com.example.justask;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

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

        /*
        Intent intent1 = new Intent();
        ComponentName cn1 = new ComponentName("com.mobisec.justask", "com.mobisec.justask.PartOne");
        intent1.setComponent(cn1);
        startActivityForResult(intent1,1);
        */

        /*
        Intent intent2 = new Intent();
        ComponentName cn2 = new ComponentName("com.mobisec.justask", "com.mobisec.justask.PartTwo");
        intent2.setComponent(cn2);
        startActivityForResult(intent2,2);
        */

        /*
        Intent intent3 = new Intent();
        ComponentName cn3 = new ComponentName("com.mobisec.justask", "com.mobisec.justask.PartThree");
        intent3.setComponent(cn3);
        startActivityForResult(intent3, 3);
        */

        Intent intent4 = new Intent();
        ComponentName cn4 = new ComponentName("com.mobisec.justask", "com.mobisec.justask.PartFour");
        intent4.setComponent(cn4);
        startActivityForResult(intent4, 4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                assert intent != null;
                Log.d("MOBISEC", intent.getStringExtra("flag"));
            }
        }

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                assert intent != null;
                Log.d("MOBISEC", intent.getStringExtra("flag"));
            }
        }

        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {

                assert intent != null;
                Set<String> keys = intent.getExtras().keySet();

                for (String s : keys) {
                    Log.i("MOBISEC", s);
                }
                Log.d("MOBISEC", intent.getStringExtra("hiddenFlag"));
            }
        }

        if (requestCode == 4) {
            if (resultCode == Activity.RESULT_OK) {
                assert intent != null;
                //Log.i("MOBISEC", intent.getStringExtra("flag"));

                Bundle b = intent.getExtras()
                        .getBundle("follow")
                        .getBundle("the")
                        .getBundle("rabbit")
                        .getBundle("hole")
                        .getBundle("deeper")
                        .getBundle("theywillneverfindthisfourthpart");

                for (String s : b.keySet()) {
                    Log.i("MOBISEC", s);
                }

                Log.d("MOBISEC", b.getBundle("deeper").getString("theywillneverfindthisfourthpart"));
            }
        }
    }
}