package net.panamiur.vieneviene.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import net.panamiur.geolocation.Geolocation;

/**
 * Created by gnu on 21/11/16.
 */

public class ServiceGeolocation extends IntentService implements LocationListener{

    private Geolocation geolocation;

    public ServiceGeolocation() {
        super("ServiceGeolocation");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        geolocation=Geolocation.getINSTANCE();
        geolocation.setContext(getApplicationContext()).setLocationListener(this);
        geolocation.startGeo();

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("GEO","geo "+location.getAccuracy());
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
