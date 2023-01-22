package com.example.frontdoor;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final boolean debug = true;
    private static final String sUrl = "http://10.0.2.2:31337/getflag";

    public static String getFlag(String username, String password) throws Exception {

        String urlParameters;
        if (debug) urlParameters = "username=testuser&password=passtestuser123";
        else urlParameters = "username=" + username + "&password=" + password;

        int postDataLength = urlParameters.getBytes(StandardCharsets.UTF_8).length;
        HttpURLConnection conn = (HttpURLConnection) new URL(sUrl + "?" + urlParameters).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder content = new StringBuilder(BuildConfig.FLAVOR);

        while (true) {
            String readLine = rd.readLine();
            if (readLine == null) {
                return content.toString();
            }
            content.append(readLine).append("\n");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Log.e("MOBISEC", getFlag("who", "cares"));
        } catch (Exception e) {
            Log.e("MOBISEC", e.toString());
        }
    }
}
