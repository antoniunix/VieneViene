package net.panamiur.vieneviene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.panamiur.vieneviene.model.ModelSelectRollDevice;
import net.panamiur.vieneviene.network.SendPush;
import net.panamiur.vieneviene.util.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class SelectRollDevice extends AppCompatActivity {

    private ModelSelectRollDevice model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_roll_device);
        init();
    }

    private void init(){
        model=new ModelSelectRollDevice(this);
    }

    public void onClickWatchDog(View v){
        startActivity(new Intent(this,ReadWriteQR.class).putExtra(Config.NAME_INTENT_EXTRAS,Config.ROL_WATCH_DOG));
        model.setRoleToSharePreference(Config.ROL_WATCH_DOG);
        finish();
    }

    public void onClickRoot(View v){
        startActivity(new Intent(this,ReadWriteQR.class).putExtra(Config.NAME_INTENT_EXTRAS,Config.ROL_ROOT));
        model.setRoleToSharePreference(Config.ROL_ROOT);
        finish();
//        new  SendPush(this).sendPushToDevice("eC4-F2uOW6w:APA91bFGXND4iB8hHzE4DTpXDLhsVRE5Yd9EDJ5aREXmxgUJatqsVrVRfZkqAOBvWI6SZVV45JdVo9KPc_VKcmE1KC_CtXXkakPzxIgxNbCq4kLRfhwgscnQsdIi6BC006ih-Yr7Ahrp","335");
    }
}
