package net.panamiur.vieneviene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.panamiur.vieneviene.model.ModelSelectRollDevice;
import net.panamiur.vieneviene.util.Config;

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
        startActivity(new Intent(this,ScanQR.class));
        model.setRoleToSharePreference(Config.ROL_WATCH_DOG);
        finish();
    }

    public void onClickRoot(View v){
        startActivity(new Intent(this,HomeMap.class));
        model.setRoleToSharePreference(Config.ROL_ROOT);
        finish();
    }
}
