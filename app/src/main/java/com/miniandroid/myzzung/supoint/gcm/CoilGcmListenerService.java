package com.miniandroid.myzzung.supoint.gcm;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;
import com.miniandroid.myzzung.supoint.util.CoilNotification;

/**
 * Created by myZZUNG
 */
public class CoilGcmListenerService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        String title = data.getString("title");
        String message = data.getString("message");
        String is_notifi = data.getString("notification_type");
        if(is_notifi.equals("true")){
            CoilNotification cnb = new CoilNotification(getApplicationContext());
            cnb.sendNotification(title, message, CoilNotification.BY_GCM);
        }
    }
}
