package com.example.jokeprovider;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/*
    Target app exposes a Content Provider.
    Content providers manages access to a central repository of data.
    Name of this provider: Joke
    Find all jokes authored by "reyammer" and concatenate them.
    We are in other words working with tables.

    A common pattern for accessing a ContentProvider
    from your UI uses a CursorLoader
 */

public class MainActivity extends AppCompatActivity {

    static final String PROVIDER_NAME = "com.mobisec.provider.Joke";
    static final String URL = "content://" + PROVIDER_NAME + "/jokes";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // creating a cursor object of the
        // content URI
        @SuppressLint("Recycle") Cursor cursor = getContentResolver().query(Uri.parse(URL), null, null, null, null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // iteration of the cursor
        // to print whole table
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                StringBuilder sb = new StringBuilder();

                String s = cursor.getString(cursor.getColumnIndex("id"));
                String s1 = cursor.getString(cursor.getColumnIndex("author"));
                String s2 = cursor.getString(cursor.getColumnIndex("joke"));

                // Print them all :)
                Log.d("MOBISEC", String.valueOf(sb.append(s).append(s1).append(s2)));

            } while (cursor.moveToNext());
        }

    }
}

// MOBISEC{lol_roftl_ahahah_:D_REYAMMER_TELLS_THE_BEST_JOKES!}
