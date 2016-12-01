package net.panamiur.vieneviene;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

import net.panamiur.vieneviene.services.GeofenceTransitionsIntentService;

import java.util.ArrayList;

public class WatchDog extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<Status>,
        LocationListener

{


    protected ArrayList<Geofence> geofenceList;

    private Button btnddGeofence;
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = 12 * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = (float) 3;
    private static final String GEOFENCE_REQ_ID = "My Geofence";
    private LocationManager locationManager;


    private Location location;
    String provider;
    double lat, lon;
    protected GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_dog);
        btnddGeofence = (Button) findViewById(R.id.btn_add_geo);
        geofenceList = new ArrayList<Geofence>();
        locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        initAcurracy();
        onLocationChanged(location);

        btnddGeofence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addGeofences();

            }
        });
        createGoogleApi();

    }

    private void initAcurracy() {

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (provider != null & !provider.equals("")) {
            location = locationManager.getLastKnownLocation(provider);

            if (location != null) {
                onLocationChanged(location);
            } else {
                Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Provider is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void createGoogleApi() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    public Geofence geofence() {
        String id="geo";

        Geofence geofence = new Geofence.Builder()
                .setRequestId(id)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_EXIT)
                .setCircularRegion(lat, lon, GEOFENCE_RADIUS_IN_METERS)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
        return geofence;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

        googleApiClient.connect();
    }
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            }

            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    Toast.makeText(
                            this,
                            "Geofences Added",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    // Get the status code for the error and log it using a user-friendly message.
                            status.getStatusCode();
                }
            }


    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        lon=location.getLongitude();
        Toast.makeText(this, "lat" + lat + "lon" + lon, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!googleApiClient.isConnecting() || !googleApiClient.isConnected()) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnecting() || googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    public void addGeofences(){
        if(!googleApiClient.isConnected()){
            Toast.makeText(this, "Google API CLIENT not connected", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            LocationServices.GeofencingApi.addGeofences(
                    googleApiClient,
                    getGeofencingRequest(),
                    getGeofencePendingIntent()
            ).setResultCallback(this);
        }catch (SecurityException e){
            Log.e("error", "error: "+ e);
        }
    }

    private GeofencingRequest getGeofencingRequest(){
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_EXIT);
        builder.addGeofence(geofence());
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent(){
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        return PendingIntent.getService(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }



}
