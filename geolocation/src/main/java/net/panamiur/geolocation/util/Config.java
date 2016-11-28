package net.panamiur.geolocation.util;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by gnu on 21/11/16.
 */

public class Config {

    public static boolean isLocationManagerActive(Context context){
        LocationManager service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return service.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
