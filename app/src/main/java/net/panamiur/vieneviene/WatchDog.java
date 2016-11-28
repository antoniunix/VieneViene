package net.panamiur.vieneviene;

import android.app.Activity;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;

import java.util.ArrayList;

public class WatchDog extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<Status> {
    protected ArrayList<Geofence> geofenceList;
    protected GoogleApiClient googleApiClient;
    private Button btnddGeofence;
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = 12 * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 20;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_dog);
        btnddGeofence = (Button) findViewById(R.id.btn_add_geo);
        geofenceList = new ArrayList<Geofence>();


    }




    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


            }

            @Override
            public void onResult(@NonNull Status status) {

            }


        }
