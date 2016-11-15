package net.panamiur.vieneviene;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by gnu on 1/11/16.
 */

public class MyAndroidFirebaseMsgService extends FirebaseMessagingService {
    private static final String TAG = "MyAndroidFCMService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Log data to Log Cat
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getData().get("id"));
        //create notification
        createNotification(remoteMessage.getData().get("send"));//remoteMessage.getNotification().getBody());
    }

    private void createNotification( String messageBody) {
//        Intent intent = new Intent( this , Home. class );
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent resultIntent = PendingIntent.getActivity( this , 0, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder( this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Android Tutorial Point FCM Tutorial")
//                .setContentText(messageBody)
//                .setAutoCancel( true )
//                .setDefaults(Notification.VISIBILITY_PRIVATE)
//                .setSound(notificationSoundURI)
//                .setContentIntent(resultIntent)
//                .setPriority(NotificationCompat.PRIORITY_HIGH);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0, mNotificationBuilder.build());
    }
}
