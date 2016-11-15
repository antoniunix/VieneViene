package net.panamiur.vieneviene.model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;
import com.google.zxing.WriterException;

import net.panamiur.readwriteqr.ConvertTextToQRCode;
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

    public ModelReadWriteQR(Context context) {
        convertTextToQRCode = new ConvertTextToQRCode();
        this.context = context;
    }



    public Bitmap getQrOfText() throws WriterException {
        DtoRegisterDeviceQrGen dtoRegisterDeviceQrGen = new DtoRegisterDeviceQrGen();

        dtoRegisterDeviceQrGen.setHashDevice(MD5.md5(Config.getIMEI(context)))
                .setRegId(getTokenFCM())
                .setType(getRoleDevice());

        return convertTextToQRCode.TextToImageEncode(new Gson().toJson(dtoRegisterDeviceQrGen));
    }

    private String getTokenFCM(){
        SharedPreferences sharedPref = context.getSharedPreferences(Config.NAME_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getString(Config.ITEM_SHP_TOKEN,"0");
    }

    private int getRoleDevice(){
        SharedPreferences sharedPref = context.getSharedPreferences(Config.NAME_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getInt(Config.ITEM_SHP_ROLE,0);
    }

    /**
     * this method validate if  qrCode is correct;
     *
     * @return 1  if correct
     * @return 2 if problem with gson malformed
     * @return 3 if type device is equals role
     * @return 4 other error
     */
    public int validateQRCode(String textQr, int role){

        try {
            DtoRegisterDeviceQrGen dto=deserializeTextQR(textQr);
            if(dto.getType()==role){
                return 3;
            }else{
                return 1;
            }

        } catch (MalformedJsonException e) {
            e.printStackTrace();
            return 2;
        }


    }

    /**
     * deserialized the text capture  of code QR to DtoRegisterDeviceQrGen
     * @param textQr capture of scannQR
     * @return DtoRegisterDeviceQrGen
     */
    private DtoRegisterDeviceQrGen deserializeTextQR(String textQr) throws MalformedJsonException {

        Type typeObjectGson = new TypeToken<DtoRegisterDeviceQrGen>() {
        }.getType();
        return new Gson().fromJson(textQr, typeObjectGson);
    }
}
