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
        int id=0;
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getData().get("id"));
        try {
            id=Integer.parseInt(remoteMessage.getData().get("id"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        makeFromId(id);
    }

    /**
     * Este metodo es el encargado de decidir que hacer con el id que llega.
     *
     * 0 error al descerealizar FCM
     * 1 registro de Watchdog a Root
     * 2 registro de Root a Watchdog
     *
     *
     * @param id
     */
    private void makeFromId( int id) {
        switch (id){
            case 1:
                break;
            case 2:
                break;

        }
    }
}
