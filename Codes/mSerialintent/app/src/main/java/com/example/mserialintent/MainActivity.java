package com.example.mserialintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mobisec.serialintent.FlagContainer;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent serialIntent = new Intent();
        serialIntent.setComponent(new ComponentName("com.mobisec.serialintent", "com.mobisec.serialintent.SerialActivity"));
        startActivityForResult(serialIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    FlagContainer flagContainer = (FlagContainer) data.getExtras().getSerializable("flag");
                    Method m = FlagContainer.class.getDeclaredMethod("getFlag");
                    m.setAccessible(true);
                    Log.d("MOBISEC", (String) m.invoke(flagContainer));

                } catch (Exception e) {
                    Log.e("MOBISEC", Log.getStackTraceString(e));
                }
            }
        }
    }
}