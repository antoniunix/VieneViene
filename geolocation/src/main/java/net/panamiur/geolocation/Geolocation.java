package net.panamiur.geolocation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import net.panamiur.geolocation.interfaces.OnApiGeolocation;

/**
 * Created by gnu on 21/11/16.
 */

public class Geolocation implements LocationListener {

    private static Geolocation INSTANCE = new Geolocation();
    private Context context;
    private LocationManager locationManager;
    private String provider;
    private int timeUpdateLocation = 1000 * 10;
    private OnApiGeolocation onApiGeolocation;
    private Location location;
    private Criteria criteria;

    private Geolocation() {
    }

    public static Geolocation getINSTANCE() {

        return INSTANCE;
    }

    public Geolocation setContext(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            context.startActivity(new Intent(context, RequestPermission.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        } else {
            Log.e("provider",provider);
            location = locationManager.getLastKnownLocation(provider);
        }

        // Initialize the location fields
        if (location != null) {
            onLocationChanged(location);
        } else {
            Log.e("GEOLOCATION", "Location not available");
        }
        return this;
    }

    public Geolocation setTimeUpdateLocation(int timeUpdateLocation) {
        this.timeUpdateLocation = timeUpdateLocation;
        return this;
    }

    public Geolocation setOnApiGeolocationListener(OnApiGeolocation onApiGeolocation) {
        this.onApiGeolocation = onApiGeolocation;
        return this;
    }

    public void startGeo() {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            context.startActivity(new Intent(context, RequestPermission.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } else {
            Log.e("GEO","StartGeo "+timeUpdateLocation);
            locationManager.requestLocationUpdates(provider, timeUpdateLocation, 1, this);
        }
    }

    public void stopGeo() {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

        Log.e("GEO","GEO API CHANGE");

        if(onApiGeolocation!=null){
            Log.e("GEO","GEO CHANGE diferente null");
            onApiGeolocation.onApiGeolocationChange(location);
        }else{
            Log.e("GEO","GEO CHANGE null");
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

        Toast.makeText(context, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {

        Toast.makeText(context, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}
