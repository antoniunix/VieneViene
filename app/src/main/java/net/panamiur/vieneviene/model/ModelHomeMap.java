package net.panamiur.vieneviene.model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import net.panamiur.vieneviene.adapter.AdapterListCar;
import net.panamiur.vieneviene.dao.DaoRootDetailOfCar;
import net.panamiur.vieneviene.dao.DaoRootLastReportsOfCar;
import net.panamiur.vieneviene.dto.DtoMessageFCMTransaction;
import net.panamiur.vieneviene.dto.DtoRootDetailOfCar;
import net.panamiur.vieneviene.dto.DtoRootLastReportsOfCar;
import net.panamiur.vieneviene.network.SendPush;
import net.panamiur.vieneviene.util.Base64Code;
import net.panamiur.vieneviene.util.Config;
import net.panamiur.vieneviene.util.MD5;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by gnu on 23/11/16.
 */

public class ModelHomeMap {

    private Context context;
    private List<DtoRootDetailOfCar> lstDetailOfCare;
    private DaoRootDetailOfCar daoRootDetailOfCar;
    private AdapterListCar adapter;

    public ModelHomeMap(Context context) {
        this.context = context;
        daoRootDetailOfCar = new DaoRootDetailOfCar(this.context);
    }

    public AdapterListCar getAdapterDetailOfCar(View.OnClickListener onClickListener,
                                                CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        lstDetailOfCare = daoRootDetailOfCar.select();
        adapter = new AdapterListCar(context, lstDetailOfCare, onClickListener, onCheckedChangeListener);
        return adapter;
    }

    public void startEarOfGod(DtoRootDetailOfCar dto) throws UnsupportedEncodingException {

        DtoMessageFCMTransaction msg = new DtoMessageFCMTransaction();
        msg.setId(Config.ID_KEY_EAR_OF_GOD)
                .setObj(dto.getPhoneNumber())
                .setHashDevice(MD5.md5(Config.getIMEI(context)));

        String encode = Base64Code.encode(new Gson().toJson(msg));
        new SendPush(context).sendPushToDevice(dto.getRegId(), encode);
    }

    public void startMovementSensor(DtoRootDetailOfCar dto) {
        DtoMessageFCMTransaction msg = new DtoMessageFCMTransaction();
        msg.setId(Config.ID_KEY_MONITORING_SLAM)
                .setObj(dto.getSensitiveMovement() + "")
                .setHashDevice(MD5.md5(Config.getIMEI(context)));

        String encode = null;
        try {
            encode = Base64Code.encode(new Gson().toJson(msg));
            new SendPush(context)
                    .sendPushToDevice(dto.getRegId(), encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void startGeolocation(DtoRootDetailOfCar dto) {
        DtoMessageFCMTransaction msg = new DtoMessageFCMTransaction();
        msg.setId(Config.ID_KEY_MONITORING_GEOLOCATION)
                .setHashDevice(MD5.md5(Config.getIMEI(context)))
                .setObj(30000 + "");

        String encode = null;
        try {
            encode = Base64Code.encode(new Gson().toJson(msg));
            new SendPush(context)
                    .sendPushToDevice(dto.getRegId(), encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addMarkerToMap(GoogleMap googleMap) {
        List<DtoRootLastReportsOfCar> listDto = new DaoRootLastReportsOfCar(context).selectLastGeo();

        Marker marker;
        googleMap.clear();
        boolean zoomFirst = true;
        for (DtoRootLastReportsOfCar dto : listDto) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(dto.getLat(), dto.getLon()))
                    .title(dto.getNameCar())).setTag(dto.getHashDevice());
            if (zoomFirst) {
                zoomFirst = false;
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dto.getLat(), dto.getLon()), 15), 2000, null);;

            }
        }
    }
}
