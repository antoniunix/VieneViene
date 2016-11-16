package net.panamiur.vieneviene.model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;
import com.google.zxing.WriterException;

import net.panamiur.readwriteqr.ConvertTextToQRCode;
import net.panamiur.vieneviene.ReadWriteQR;
import net.panamiur.vieneviene.dto.DtoRegisterDeviceQrGen;
import net.panamiur.vieneviene.util.Config;
import net.panamiur.vieneviene.util.MD5;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by gnu on 8/11/16.
 */

public class ModelReadWriteQR {

    private ConvertTextToQRCode convertTextToQRCode;
    private Context context;
    private Activity activity;
    private final int DESERIALIZE_OK=1;
    private final int DESERIALIZE_MALFORMED=2;
    private final int DESERIALIZE_IQUALS_ROLE=3;

    public ModelReadWriteQR(ReadWriteQR activity) {
        convertTextToQRCode = new ConvertTextToQRCode();
        this.context = activity.getApplicationContext();
        this.activity=activity;
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
     * this method validate if  qrCode is correct;
     *
     * @return DESERIALIZE_OK  if correct
     * @return DESERIALIZE_MALFORMED if problem with json malformed
     * @return DESERIALIZE_IQUALS_ROLE if type device is equals role
     * @return DESERIALIZE_OTHER_ERROR other error
     */
    public int validateQRCode(String textQr, int role){

        try {
            DtoRegisterDeviceQrGen dto=deserializeTextQR(textQr);
            if(dto.getType()==role){
                return DESERIALIZE_IQUALS_ROLE;
            }else{
                return DESERIALIZE_OK;
            }

        } catch (MalformedJsonException e) {
            e.printStackTrace();
            return DESERIALIZE_MALFORMED;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return DESERIALIZE_MALFORMED;
        }


    }

    /**
     * deserialized the text capture  of code QR to DtoRegisterDeviceQrGen
     * @param textQr capture of scannQR
     * @return DtoRegisterDeviceQrGen
     */
    public DtoRegisterDeviceQrGen deserializeTextQR(String textQr) throws MalformedJsonException,JsonSyntaxException {

        Type typeObjectGson = new TypeToken<DtoRegisterDeviceQrGen>() {
        }.getType();
        return new Gson().fromJson(textQr, typeObjectGson);
    }


    /**
     * El codigo QR debe ser redimencionado al tamaño de la pantalla
     * @return el tamaño al debe ser el codigo QR
     */
    private int calculateSizeQRCode(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels/3;
    }
}
