package net.panamiur.vieneviene.util;

import android.Manifest;
import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by gnu on 9/11/16.
 */

public class Config {

    public static final int ROL_WATCH_DOG=100;
    public static final int ROL_ROOT=50;

    public static final String NAME_SHARE_PREFERENCE="VIENE_VIENE_SHP";
    public static final String ITEM_SHP_TOKEN="ITEM_SHP_TOKEN";
    public static final String ITEM_SHP_ROLE="ITEM_SHP_ROLE";


    public static final String DB_NAME="vieneviene.db";
    public static final int VERSION_DB=1;

    public static final String API_KEY_SERVER="AIzaSyB3LT8zyjYim7UXSJ53nCI6LTS5JfoClGY";


    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId() != null ? telephonyManager.getDeviceId() : "0";
    }

    public static final int ID_KEY_REGISTRY=1;
    public static final int ID_KEY_MONITORING_SLAM=2;
    public static final int ID_KEY_REPORT_CAR_DANGER=3;

}
