package com.miniandroid.myzzung.supoint.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by myZZUNG
 */
public class CoilInstanceIDListenerService extends InstanceIDListenerService {

    private static final String TAG = "MyInstanceIDLS";

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegisterationIntentService.class);
        startService(intent);
    }
}
