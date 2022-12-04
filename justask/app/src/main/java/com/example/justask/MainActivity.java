package com.example.justask;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

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

    }
}