package net.panamiur.vieneviene;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.stetho.Stetho;

import net.panamiur.vieneviene.util.Config;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private final static Long TIME_OF_SHOW_VIDEO=2700L;

    private Timer timer;
    private Context context;
    private WeakReference<Splash> weakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    public void init(){
        context=this;
        timer = new Timer();
        weakReference = new WeakReference<>(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    @Override
    protected void onResume() {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Splash activity = weakReference.get();
                    if (activity != null && !activity.isFinishing()) {

                        switch (getRole()){
                            case Config.ROL_ROOT:
                                startActivity(new Intent(context, HomeMap.class));
                                break;
                            case Config.ROL_WATCH_DOG:
                                startActivity(new Intent(context, WatchDog.class));
                                break;
                            default:
                                startActivity(new Intent(context, SelectRollDevice.class));
                                break;
                        }
                        finish();
                    }
                }
            };
            timer.schedule(timerTask, TIME_OF_SHOW_VIDEO);

        super.onResume();
    }

    private int getRole(){
        SharedPreferences sharedPref = context.getSharedPreferences(Config.NAME_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getInt(Config.ITEM_SHP_ROLE,0);
    }
}
