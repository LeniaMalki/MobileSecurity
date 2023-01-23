package com.example.filehasher;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/*
Problem definition:

1. Define an activity with an intent filer for the "com.mobisec.intent.action.HASHFILE" action.
    - System will ask for hashing file (stored on SDCARD)
    - File path is specified by Uri part of intent you receive (access through  Intent.getData())
2. Put calculated hash in a result intent, under the "hash" key and in hexadecimal format.

If the expected hash and the one from your app match, the flag will be printed in the logs.
 */

public class HashActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            String s = getIntent().getData().toString().substring(7);
            Path path = Paths.get(s);
            byte[] data = Files.readAllBytes(path);
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                Log.d("MOBISEC", Arrays.toString(e.getStackTrace()));
            }

            assert digest != null;
            byte[] encodedhash = digest.digest(data);
            Log.d("MOBISEC", Arrays.toString(encodedhash));
            Log.d("MOBISEC", convertToHex(encodedhash));

            Intent resultIntent = new Intent();
            resultIntent.putExtra("hash", convertToHex(encodedhash));
            setResult(Activity.RESULT_OK, resultIntent);
            finish();

        } catch (IOException e) {
            Log.d("MOBISEC", Arrays.toString(e.getStackTrace()));
        }

    }

    private String convertToHex(byte[] hash) {
        Log.d("MOBISEC", "Inside convertToHex()");
        StringBuilder sb = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String s = Integer.toHexString(0xff & b);
            if (s.length() == 1) {
                sb.append('0');
            }
            sb.append(s);
        }
        return sb.toString();
    }

}
