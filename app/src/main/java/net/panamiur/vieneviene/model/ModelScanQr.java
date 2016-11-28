package net.panamiur.vieneviene.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;

import net.panamiur.vieneviene.ScanQR;
import net.panamiur.vieneviene.dao.DaoWtdDetailDeviceToReport;
import net.panamiur.vieneviene.dto.DtoMessageFCMTransaction;
import net.panamiur.vieneviene.dto.DtoWtdDetailDeviceToReport;
import net.panamiur.vieneviene.network.SendPush;
import net.panamiur.vieneviene.util.Base64Code;
import net.panamiur.vieneviene.util.Config;
import net.panamiur.vieneviene.util.MD5;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * Created by gnu on 8/11/16.
 */

public class ModelScanQr {


    private Context context;
    private Activity activity;

    public ModelScanQr(ScanQR activity) {

        this.context = activity.getApplicationContext();
        this.activity = activity;
    }

    /**
     * this method validate if  qrCode is correct;
     *
     * @return true if ok
     */
    public boolean validateQRCode(String textQr) {

        try {
            deserializeTextQR(textQr);
            return true;

        } catch (MalformedJsonException e) {
            e.printStackTrace();
            return false;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * deserialized the text capture  of code QR to DtoRegisterDeviceQrGen
     *
     * @param textQr capture of scannQR
     * @return DtoRegisterDeviceQrGen
     */
    public DtoWtdDetailDeviceToReport deserializeTextQR(String textQr) throws MalformedJsonException, JsonSyntaxException {

        Type typeObjectGson = new TypeToken<DtoWtdDetailDeviceToReport>() {
        }.getType();
        return new Gson().fromJson(textQr, typeObjectGson);
    }


    /**
     * cuando se escanea un dispoditivo root debe ser guardada la informacion, pero antes debe eliminarse si es que existe registro,
     * esto es por que un watchdog solo puede avisar a un root.
     *
     * @return 0 will error
     */
    public int registerDeviceRootInDB(DtoWtdDetailDeviceToReport dto) {
        new DaoWtdDetailDeviceToReport(context).delete();
        return new DaoWtdDetailDeviceToReport(context).insert(dto);
    }

    public void sendRegistryFCM() throws UnsupportedEncodingException {
        DtoMessageFCMTransaction msg=new DtoMessageFCMTransaction();
        msg.setId(Config.ID_KEY_REGISTRY)
                .setObj(getRegIdWatchDog())
                .setHashDevice(MD5.md5(Config.getIMEI(context)))
                .setModelDevice(Build.BRAND+" "+android.os.Build.MODEL);

        String encode= Base64Code.encode(new Gson().toJson(msg));
        new SendPush(context).sendPushToDevice(new DaoWtdDetailDeviceToReport(context).select().getRegId(),encode);
    }

    private String getRegIdWatchDog(){
        SharedPreferences sharedPref = context.getSharedPreferences(Config.NAME_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getString(Config.ITEM_SHP_TOKEN,"0");
    }
}
