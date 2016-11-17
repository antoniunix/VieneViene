package net.panamiur.vieneviene.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;

import net.panamiur.vieneviene.ScanQR;
import net.panamiur.vieneviene.dao.DaoDeviceRoleRootDetail;
import net.panamiur.vieneviene.dto.DtoRegisterDeviceQrGen;
import net.panamiur.vieneviene.util.Config;

import java.lang.reflect.Type;

/**
 * Created by gnu on 8/11/16.
 */

public class ModelReadWriteQR {


    private Context context;
    private Activity activity;

    public ModelReadWriteQR(ScanQR activity) {

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
    public DtoRegisterDeviceQrGen deserializeTextQR(String textQr) throws MalformedJsonException, JsonSyntaxException {

        Type typeObjectGson = new TypeToken<DtoRegisterDeviceQrGen>() {
        }.getType();
        return new Gson().fromJson(textQr, typeObjectGson);
    }


    /**
     * cuando se escanea un dispoditivo root debe ser guardada la informacion, pero antes debe eliminarse si es que existe registro,
     * esto es por que un watchdog solo puede avisar a un root.
     *
     * @return 0 will error
     */
    public int registerDeviceRootInDB(DtoRegisterDeviceQrGen dto) {
        new DaoDeviceRoleRootDetail(context).delete();
        return new DaoDeviceRoleRootDetail(context).insert(dto);
    }
}
