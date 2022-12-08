package com.example.whereareyou;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WhereAreYou extends Service {
    private LocationManager locationManager;

    @Override
    public void onCreate() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission") // This is not standard practise
    @Override
    /*
    Called by the system every time a client explicitly starts the service
    by calling Context.startService(Intent)
     */ public int onStartCommand(Intent intent, int flags, int startId) {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Intent i = new Intent();
                i.setAction("com.mobisec.intent.action.LOCATION_ANNOUNCEMENT");
                i.putExtra("location", location);
                sendBroadcast(i);
                Log.i("MOBISEC", String.valueOf(location));

            }
        });
        return Service.START_STICKY_COMPATIBILITY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
