package com.example.myfrontdoor;

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

    private static final String sUrl = "http://10.0.2.2:31337/getflag";
    private static final boolean debug = true;


    public static String getFlag(String username, String password) throws Exception {

        String urlParameters;
        if (debug) {
            urlParameters = "username=testuser&password=passtestuser123";
        } else {
            urlParameters = "username=" + username + "&password=" + password;
        }
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = sUrl + "?" + urlParameters;
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String content = "";
        while (true) {
            String line = rd.readLine();
            if (line != null) {
                content = content + line + "\n";
            } else {
                return content;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Remove the thread restriction and override the default behavior
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);


        try {
            Log.e("MOBISEC", getFlag("username", "password"));
        } catch (Exception e) {
            Log.e("MOBISEC", e.toString());
        }
    }
}