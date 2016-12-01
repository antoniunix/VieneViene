package net.panamiur.vieneviene.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import net.panamiur.vieneviene.R;
import net.panamiur.vieneviene.WatchDog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonardo on 28/11/16.
 */

public class GeofenceTransitionsIntentService extends IntentService {

    protected static final String TAG = "GeofenceTransitionsIS";


    public GeofenceTransitionsIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
        if (event.hasError()) {
            Log.e(TAG, "Errot: " + event.getErrorCode());
            return;
        }
        String description = getGeofenceTransitionDetails(event);
        sendNotification(description);
    }

    private static String getGeofenceTransitionDetails(GeofencingEvent event) {
        String transition = GeofenceStatusCodes.getStatusCodeString(event.getGeofenceTransition());

        List triggerinIDs = new ArrayList();
        for (Geofence geofence : event.getTriggeringGeofences()) {
            triggerinIDs.add(geofence.getRequestId());
        }
        Log.e("geo", "size"+triggerinIDs.size());
        return String.format("%s: %s", transition, TextUtils.join(", ", triggerinIDs));
    }

    private void sendNotification(String notificationDetails) {
        Intent notificationIntent = new Intent(getApplicationContext(), WatchDog.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(WatchDog.class).addNextIntent(notificationIntent);
        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle(notificationDetails)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Salio del radio")
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }
}
