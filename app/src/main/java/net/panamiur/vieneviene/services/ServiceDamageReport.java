package net.panamiur.vieneviene.services;

import android.app.IntentService;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.gson.Gson;

import net.panamiur.movementreport.Movement;
import net.panamiur.movementreport.OnSlamListener;
import net.panamiur.vieneviene.R;
import net.panamiur.vieneviene.dao.DaoWtdDetailDeviceToReport;
import net.panamiur.vieneviene.dto.DtoMessageFCMTransaction;
import net.panamiur.vieneviene.network.SendPush;
import net.panamiur.vieneviene.util.Base64Code;
import net.panamiur.vieneviene.util.Config;
import net.panamiur.vieneviene.util.MD5;

import java.io.UnsupportedEncodingException;

/**
 * Created by gnu on 18/11/16.
 */

public class ServiceDamageReport extends IntentService implements OnSlamListener {

    private Movement movement;

    private float sensibility;

    public ServiceDamageReport() {
        super("ServiceDamageReport");
        Log.d("servicio", "Constructor " + sensibility);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sensibility = intent.getFloatExtra(Config.NAME_SHARE_PREFERENCE, 1.0f);
        movement = Movement.getInstance();
        movement.setContext(getApplicationContext()).setOnSlamListener(this).setSensibility(sensibility);
        movement.initService();
    }

    @Override
    public void onSlamListener(double axisX, double axisY, double axisZ) {

        if (isTimeSendDamageReport()) {
            DtoMessageFCMTransaction msg = new DtoMessageFCMTransaction();
            msg.setId(Config.ID_KEY_REPORT_CAR_DANGER)
                    .setHashDevice(MD5.md5(Config.getIMEI(getApplicationContext())));

            String encode = null;
            try {
                encode = Base64Code.encode(new Gson().toJson(msg));
                new SendPush(getApplicationContext())
                        .sendPushToDevice(
                                new DaoWtdDetailDeviceToReport(getApplicationContext()).select().getRegId(), encode);
                saveLastSalamTime();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

    }

    private void saveLastSalamTime() {
        SharedPreferences sharedPref = getSharedPreferences(Config.NAME_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(Config.ITEM_SHP_LAST_SLAM, System.currentTimeMillis());
        editor.commit();
    }

    private boolean isTimeSendDamageReport() {
        SharedPreferences sharedPref = getSharedPreferences(Config.NAME_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return (System.currentTimeMillis()-sharedPref.getLong(Config.ITEM_SHP_LAST_SLAM, 0) )> Config.TIME_SEND_DAMAGE_REPORT;
    }
}
