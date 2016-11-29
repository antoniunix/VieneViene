package net.panamiur.vieneviene;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.panamiur.geolocation.Geolocation;
import net.panamiur.movementreport.Movement;
import net.panamiur.vieneviene.dao.DaoRootDetailOfCarTemp;
import net.panamiur.vieneviene.dao.DaoRootLastReportsOfCar;
import net.panamiur.vieneviene.dialogs.CarInDamage;
import net.panamiur.vieneviene.dto.DtoMessageFCMTransaction;
import net.panamiur.vieneviene.dto.DtoNewEvent;
import net.panamiur.vieneviene.dto.DtoRootDetailOfCarTemp;
import net.panamiur.vieneviene.dto.DtoRootLastReportsOfCar;
import net.panamiur.vieneviene.services.ServiceDamageReport;
import net.panamiur.vieneviene.services.ServiceGeolocation;
import net.panamiur.vieneviene.util.Base64Code;
import net.panamiur.vieneviene.util.Config;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import de.greenrobot.event.EventBus;

/**
 * Created by gnu on 1/11/16.
 */

public class MyAndroidFirebaseMsgService extends FirebaseMessagingService {
    private static final String TAG = "MyAndroidFCMService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        DtoMessageFCMTransaction dtoMessageFCMTransaction = null;
//        dtoMessageFCMTransaction=new DtoMessageFCMTransaction();
//        dtoMessageFCMTransaction.setId(2);
        Log.d(TAG, "Notification Message ID: " + remoteMessage.getMessageId());
//        processMessage(dtoMessageFCMTransaction);
        Type typeObjectGson = new TypeToken<DtoMessageFCMTransaction>() {
        }.getType();
        String msg;
        try {
            msg = Base64Code.decode(remoteMessage.getData().get("msg"));
            Log.d(TAG, "Notification Message Body: " + msg);
            dtoMessageFCMTransaction = new Gson().fromJson(msg, typeObjectGson);
            processMessage(dtoMessageFCMTransaction);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo es el encargado de decidir que hacer con el id que llega del dto.
     * <p>
     * 0 error al descerealizar FCM
     * 1 registro de Watchdog a Root
     * 2 iniciar monitoreo de golpe
     * 3 reporte de golpe
     * 4 activar geolocation
     * 5 oido de dios
     */
    private void processMessage(DtoMessageFCMTransaction dtoMessageFCMTransaction) {
        switch (dtoMessageFCMTransaction.getId()) {
            case Config.ID_KEY_REGISTRY:
                DtoRootDetailOfCarTemp dtoTmp = new DtoRootDetailOfCarTemp();
                dtoTmp.setRegId(dtoMessageFCMTransaction.getObj())
                        .setHashDevice(dtoMessageFCMTransaction.getHashDevice())
                        .setDateReceived(System.currentTimeMillis() + "")
                        .setModelDevice(dtoMessageFCMTransaction.getModelDevice());
                new DaoRootDetailOfCarTemp(getApplicationContext()).delete(dtoMessageFCMTransaction.getHashDevice());
                new DaoRootDetailOfCarTemp(getApplicationContext()).insert(dtoTmp);


                startActivity(new Intent(getApplicationContext(), DialogCompleteRegister.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case Config.ID_KEY_MONITORING_SLAM:
                try {
                    startService(new Intent(getApplicationContext(), ServiceDamageReport.class)
                            .putExtra(Config.NAME_SHARE_PREFERENCE,
                                    Float.valueOf(dtoMessageFCMTransaction.getObj())));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Config.ID_KEY_REPORT_CAR_DANGER:
                startActivity(new Intent(getApplicationContext(), CarInDamage.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case Config.ID_KEY_MONITORING_GEOLOCATION:
                try {
                    startService(new Intent(getApplicationContext(), ServiceGeolocation.class)
                            .putExtra(Config.NAME_SHARE_PREFERENCE,
                                    Integer.valueOf(dtoMessageFCMTransaction.getObj())));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Config.ID_KEY_EAR_OF_GOD:
                startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+dtoMessageFCMTransaction.getObj())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                break;
            case Config.ID_KEY_STOP_MONITORING_SLAM:
                Movement.getInstance().stopService();
//                getApplicationContext().stopService(new Intent(getApplicationContext(),ServiceDamageReport.class));
                break;
            case Config.ID_KEY_REPORT_GEOLOCATION:
                DtoRootLastReportsOfCar dtoRootLastReportsOfCar=new DtoRootLastReportsOfCar();
                dtoRootLastReportsOfCar.setHashDevice(dtoMessageFCMTransaction.getHashDevice())
                        .setLat(dtoMessageFCMTransaction.getLat())
                        .setLon(dtoMessageFCMTransaction.getLon())
                        .setDateCapture(dtoMessageFCMTransaction.getTime()+"");
                new DaoRootLastReportsOfCar(getApplicationContext()).insert(dtoRootLastReportsOfCar);
                EventBus.getDefault().post(new DtoNewEvent("new geo"));

                break;
            case Config.ID_KEY_STOP_MONITORING_GEOLOCATION:
                Geolocation.getINSTANCE().stopGeo();
                break;
        }
    }
}
