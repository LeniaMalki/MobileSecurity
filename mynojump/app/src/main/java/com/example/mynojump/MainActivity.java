package com.example.mynojump;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String msg = "Main-to-A/A-to-B/B-to-C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent();
        i.setComponent(new ComponentName("com.mobisec.nojumpstarts", "com.mobisec.nojumpstarts.C"));
        i.putExtra("authmsg", msg);
        try {
            i.putExtra("authsign", Main.sign(msg));
        } catch (Exception e) {
            Log.w("MOBISEC", Log.getStackTraceString(e));
        }
        startActivityForResult(i, 1);
    }

    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        String str = paramIntent.getStringExtra("flag");
        Log.d("MOBISEC", str);
    }
}