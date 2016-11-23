package net.panamiur.vieneviene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.zxing.WriterException;

import net.panamiur.geolocation.Geolocation;
import net.panamiur.vieneviene.dao.DaoWtdDetailDeviceToReport;
import net.panamiur.vieneviene.dto.DtoMessageFCMTransaction;
import net.panamiur.vieneviene.model.ModelBarCodeShow;
import net.panamiur.vieneviene.network.SendPush;
import net.panamiur.vieneviene.services.ServiceGeolocation;
import net.panamiur.vieneviene.util.Base64Code;
import net.panamiur.vieneviene.util.Config;
import net.panamiur.vieneviene.util.MD5;

import java.io.UnsupportedEncodingException;

public class BarCodeShow extends AppCompatActivity {

    private ImageView img_qr_wd;
    private ModelBarCodeShow model;
    private EditText txttemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_show);
        init();
    }

    private void init(){
        model=new ModelBarCodeShow(this);
        img_qr_wd=(ImageView)findViewById(R.id.img_qr_wd);
        txttemp=(EditText)findViewById(R.id.txttemp);

        try {
            img_qr_wd.setImageBitmap(model.getQrOfText());
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void onClickContinue(View v){
        finish();
    }

    public void onClicktmp(View v){
        DtoMessageFCMTransaction msg = new DtoMessageFCMTransaction();
        msg.setId(Config.ID_KEY_MONITORING_SLAM)
                .setObj(txttemp.getText().toString())
                .setHashDevice(MD5.md5(Config.getIMEI(this)));

        String encode = null;
        try {
            encode = Base64Code.encode(new Gson().toJson(msg));
            new SendPush(getApplicationContext())
                    .sendPushToDevice("f6lQOZNTRdA:APA91bHVTXfhukdkZuG5AfpUTwStFbGDq3pxyOUSgRZxGOMbGeHubt8VcDhyo7AvAFzYCuVUD11WMUfOACK5E1NAbj3wjEhkh5yUFeXxM9CMhXpu9e1eJvv1B_ikvuoBUG_juYmCbSox", encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //startService(new Intent(this, ServiceGeolocation.class));
    }
}
