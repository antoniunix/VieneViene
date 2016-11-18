package net.panamiur.vieneviene;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import net.panamiur.vieneviene.util.Base64Code;

import java.io.UnsupportedEncodingException;

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
        try {
            Log.d(TAG, "Notification Message Body: " + Base64Code.decode(remoteMessage.getData().get("msg")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        try {
//            id=Integer.parseInt(remoteMessage.getData().get("id"));
//        }catch (NumberFormatException e){
//            e.printStackTrace();
//        }
//
//        makeFromId(id);
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
