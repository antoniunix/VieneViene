package net.panamiur.vieneviene.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import net.panamiur.geolocation.Geolocation;
import net.panamiur.vieneviene.dao.DaoWtdDetailDeviceToReport;
import net.panamiur.vieneviene.dto.DtoMessageFCMTransaction;
import net.panamiur.vieneviene.network.SendPush;
import net.panamiur.vieneviene.util.Base64Code;
import net.panamiur.vieneviene.util.Config;
import net.panamiur.vieneviene.util.MD5;

import java.io.UnsupportedEncodingException;

/**
 * Created by gnu on 21/11/16.
 */

public class ServiceGeolocation extends IntentService implements LocationListener{

    private Geolocation geolocation;
    private int timeUpdateLocation;

    public ServiceGeolocation() {
        super("ServiceGeolocation");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        timeUpdateLocation=intent.getIntExtra(Config.NAME_SHARE_PREFERENCE,10000);
        geolocation=Geolocation.getINSTANCE();
        geolocation.setContext(getApplicationContext()).setLocationListener(this).setTimeUpdateLocation(timeUpdateLocation);
        geolocation.startGeo();

    }

    @Override
    public void onLocationChanged(Location location) {

        DtoMessageFCMTransaction msg = new DtoMessageFCMTransaction();
        msg.setId(Config.ID_KEY_REPORT_GEOLOCATION)
                .setHashDevice(MD5.md5(Config.getIMEI(getApplicationContext())))
                .setLat(location.getLatitude())
                .setLon(location.getLongitude())
                ;

        String encode = null;
        try {
            encode = Base64Code.encode(new Gson().toJson(msg));
            new SendPush(getApplicationContext())
                    .sendPushToDevice(
                            new DaoWtdDetailDeviceToReport(getApplicationContext()).select().getRegId(), encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
