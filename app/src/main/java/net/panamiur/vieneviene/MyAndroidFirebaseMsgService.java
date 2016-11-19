package net.panamiur.vieneviene;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.panamiur.vieneviene.dao.DaoRootDetailOfCarTemp;
import net.panamiur.vieneviene.dto.DtoMessageFCMTransaction;
import net.panamiur.vieneviene.dto.DtoRootDetailOfCarTemp;
import net.panamiur.vieneviene.util.Base64Code;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * Created by gnu on 1/11/16.
 */

public class MyAndroidFirebaseMsgService extends FirebaseMessagingService {
    private static final String TAG = "MyAndroidFCMService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        DtoMessageFCMTransaction dtoMessageFCMTransaction=null;
        Type typeObjectGson = new TypeToken<DtoMessageFCMTransaction>() {}.getType();
        String msg;
        try {
            msg=Base64Code.decode(remoteMessage.getData().get("msg"));
            Log.d(TAG, "Notification Message Body: " +msg);
            dtoMessageFCMTransaction=new Gson().fromJson(msg, typeObjectGson);
            processMessage(dtoMessageFCMTransaction);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo es el encargado de decidir que hacer con el id que llega del dto.
     *
     * 0 error al descerealizar FCM
     * 1 registro de Watchdog a Root
     * 2 registro de Root a Watchdog
     *
     *
     *
     */
    private void processMessage(DtoMessageFCMTransaction dtoMessageFCMTransaction) {
        switch (dtoMessageFCMTransaction.getId()){
            case 1:
                DtoRootDetailOfCarTemp dtoTmp=new DtoRootDetailOfCarTemp();
                dtoTmp.setRegId(dtoMessageFCMTransaction.getObj())
                        .setHashDevice(dtoMessageFCMTransaction.getHashDevice())
                        .setDateReceived(System.currentTimeMillis()+"")
                        .setModelDevice(dtoMessageFCMTransaction.getModelDevice());
                new DaoRootDetailOfCarTemp(getApplicationContext()).delete(dtoMessageFCMTransaction.getHashDevice());
                new DaoRootDetailOfCarTemp(getApplicationContext()).insert(dtoTmp);


                startActivity(new Intent(getApplicationContext(),DialogCompleteRegister.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }
}
