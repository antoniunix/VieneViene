package net.panamiur.vieneviene.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.zxing.WriterException;

import net.panamiur.readwriteqr.ConvertTextToQRCode;
import net.panamiur.vieneviene.BarCodeShow;
import net.panamiur.vieneviene.dto.DtoRegisterDeviceQrGen;
import net.panamiur.vieneviene.util.Config;
import net.panamiur.vieneviene.util.MD5;

/**
 * Created by gnu on 16/11/16.
 */

public class ModelBarCodeShow {

    private ConvertTextToQRCode convertTextToQRCode;
    private Context context;
    private Activity activity;

    public ModelBarCodeShow(BarCodeShow activity) {
        convertTextToQRCode = new ConvertTextToQRCode();
        this.activity=activity;
        this.context=activity.getApplicationContext();
    }

    /**
     *  make a QR code from text,
     * @return code QR
     * @throws WriterException
     */
    public Bitmap getQrOfText() throws WriterException {
        DtoRegisterDeviceQrGen dtoRegisterDeviceQrGen = new DtoRegisterDeviceQrGen();

        dtoRegisterDeviceQrGen.setHashDevice(MD5.md5(Config.getIMEI(context)))
                .setRegId(getTokenFCM())
                .setType(getRoleDevice());

        return convertTextToQRCode.TextToImageEncode(new Gson().toJson(dtoRegisterDeviceQrGen),calculateSizeQRCode());
    }

    private String getTokenFCM(){
        SharedPreferences sharedPref = context.getSharedPreferences(Config.NAME_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getString(Config.ITEM_SHP_TOKEN,"0");
    }

    /**
     * get role the device saved in sharepreference, if don't exist retur 0
     * @return 0 id don't exist
     *
     */
    private int getRoleDevice(){
        SharedPreferences sharedPref = context.getSharedPreferences(Config.NAME_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getInt(Config.ITEM_SHP_ROLE,0);
    }

    /**
     * El codigo QR debe ser redimencionado al tamaño de la pantalla
     * @return el tamaño al debe ser el codigo QR
     */
    private int calculateSizeQRCode(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels/2;
    }
}
