package com.example.reachingout;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/*
There is an HTTP server listening on 10.0.2.2:31337
(reachable from within the emulator where your app is running).
The flag is there. It's easy, but you may need to pro-up your math skills.
 */
public class MainActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestBody formBody = new FormBody.Builder()
                .add("val1", "3")
                .add("oper", "+")
                .add("val2", "6")
                .add("answer", "9")
                .build();

        Request request = new Request.Builder()
                .url("http://10.0.2.2:31337/flag")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    assert responseBody != null;
                    Log.i("MOBISEC", responseBody.string());
                }
            }
        });

    }
}
