package net.panamiur.geolocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by gnu on 21/11/16.
 */

public class Geolocation implements LocationListener {

    private static Geolocation INSTANCE=new Geolocation();
    private Context context;
    private LocationManager locationManager;
    private String provider;
    private int timeUpdateLocation=1000*10;
    private LocationListener locationListener;

    private Geolocation() {
    }

    public static Geolocation getINSTANCE(){

        return INSTANCE;
    }
    public Geolocation setContext(Context context){
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            Log.e("GEOLOCATION","Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            Log.e("GEOLOCATION","Location not available");
            Log.e("GEOLOCATION","Location not available");
        }
        return this;
    }

    public Geolocation setTimeUpdateLocation(int timeUpdateLocation){
        this.timeUpdateLocation=timeUpdateLocation;
        return this;
    }

    public Geolocation setLocationListener(LocationListener locationListener){
        this.locationListener=locationListener;
        return this;
    }

    public void startGeo(){
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.requestLocationUpdates(provider,1000*10,0, this);
    }

    public void stopGeo(){
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

        if(locationListener!=null){
            locationListener.onLocationChanged(location);
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
