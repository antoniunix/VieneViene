package net.panamiur.movementreport;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gnu on 18/11/16.
 */

public class Movement implements SensorEventListener {


    private static final Movement INSTANCE = new Movement();
    private Context context;
    private SensorManager sensorManager;
    private Float max, may, maz;//aqui se guardaran los primero valores capturados, para ser comparados con los posteriores
    private float ax, ay, az;
    private final int TIME_OF_START_INIT_MONITOR = 1000 * 5;
    private float sensibility = 0.80f;
    private OnSlamListener onSlamListener;

    private Movement() {
    }

    public static Movement getInstance() {
        return INSTANCE;
    }

    public Movement setContext(Context context) {
        this.context = context;
        return this;
    }


    public Movement setOnSlamListener(OnSlamListener onSlamListener) {
        this.onSlamListener = onSlamListener;
        return this;
    }

    public Movement setSensibility(float sensibility) {
        this.sensibility = sensibility;
        return this;
    }

    public void initService() {

        Log.d("init", "Inet scann movement");
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
                sensorManager.registerListener(Movement.this,
                        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                        SensorManager.SENSOR_DELAY_NORMAL);
            }
        };
        timer.schedule(timerTask, TIME_OF_START_INIT_MONITOR);

    }

    public void stopService() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);

        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (max == null || may == null || maz == null) {
                max = sensorEvent.values[0];
                may = sensorEvent.values[1];
                maz = sensorEvent.values[2];
            } else if ((sensorEvent.values[0] > (max + sensibility) || sensorEvent.values[0] < (max - sensibility))
                    || (sensorEvent.values[1] > (may + sensibility) || sensorEvent.values[1] < (may - sensibility))
                    || (sensorEvent.values[2] > (maz + sensibility) || sensorEvent.values[2] < (maz - sensibility))) {
                Log.e("ACELEROMETER", "GOLPEEE");
                if (onSlamListener != null) {
                    onSlamListener.onSlamListener(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                }
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
