package com.example.unbindable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // MOBISEC{please_respect_my_will_you_shall_not_bind_me_my_friend}

    /**
     * Commands to the service to display a message
     */
    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_SET_VALUE = 3;
    static final int MSG_GET_FLAG = 4;
    /**
     * Messenger for communicating with the service.
     */
    Messenger outgoingMessage = null;
    Messenger incomingMessage = new Messenger(new mHandler());
    /**
     * Flag indicating whether we have called bind on the service.
     */
    boolean bound;
    /**
     * Class for interacting with the main interface of the service.
     */
    private final ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.i("MOBISEC", "_________ Running onServiceConnected _________");

            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            outgoingMessage = new Messenger(service);
            bound = true;

            try {
                Message msg = Message.obtain(null, MSG_REGISTER_CLIENT);
                msg.replyTo = incomingMessage;
                outgoingMessage.send(msg);

                // Give it some value as an example.
                Message msg1 = Message.obtain(null, MSG_SET_VALUE, 4, 4);
                msg1.replyTo = incomingMessage;
                outgoingMessage.send(msg1);

                Message msg2 = Message.obtain(null, MSG_GET_FLAG);
                msg2.replyTo = incomingMessage;
                outgoingMessage.send(msg2);

            } catch (RemoteException e) {
                Log.d("MOBISEC", "Error on start");
            }


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            outgoingMessage = null;
            bound = false;
            Log.i("MOBISEC", "_________ Ran onServiceDisconnected _________");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setClassName("com.mobisec.unbindable", "com.mobisec.unbindable.UnbindableService");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);


    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to the service
        bindService(new Intent(this, MainActivity.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    static class mHandler extends Handler {

        public mHandler() {
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.d("MOBISEC", "_________ handleMessage _________");

            if (msg.what == MSG_GET_FLAG) {
                Bundle mBundle = (Bundle) msg.obj;
                Log.d("MOBISEC", "FLAG " + mBundle.getString("flag"));
            }
        }

    }
}

