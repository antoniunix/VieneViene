package net.panamiur.vieneviene;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import net.panamiur.vieneviene.dto.DtoRootDetailOfCar;
import net.panamiur.vieneviene.model.ModelHomeMap;
import net.panamiur.vieneviene.util.Config;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author  antoniunix
 * Esta clase se muestra solo en el ROLEDEVICE ROOT, ya que se desplegara el codigo QR con la informacion
 * del REG ID FCM y hash del dispocitivo
 */
public class HomeMap extends AppCompatActivity implements
        Toolbar.OnMenuItemClickListener,
        OnMapReadyCallback,View.OnClickListener,
        CompoundButton.OnCheckedChangeListener{

    private ModelHomeMap model;
    private Toolbar toolbar;
    private ListView lst_car;
    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_map);
        init();
    }

    private void init(){
        model=new ModelHomeMap(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lst_car=(ListView)findViewById(R.id.lst_car);
        drawer_layout=(DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        lst_car.setAdapter(model.getAdapterDetailOfCar(this,this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_map, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId()==R.id.add_car){
            startActivity(new Intent(this,BarCodeShow.class));
        }else if(item.getItemId()==R.id.setting){
            startActivity(new Intent(this,SelectRollDevice.class));
            finish();
        }

        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btn_config){
            DtoRootDetailOfCar dto=(DtoRootDetailOfCar)view.getTag();
            startActivity(new Intent(this,DialogCompleteRegister.class).putExtra(Config.NAME_SHARE_PREFERENCE,dto));
        }else if(view.getId()==R.id.btn_ear_of_good){
            DtoRootDetailOfCar dto=(DtoRootDetailOfCar)view.getTag();
            if(dto.getPhoneNumber()==null || dto.getPhoneNumber().isEmpty()){
                Toast.makeText(this,getResources().getString(R.string.warning_ear_of_god),Toast.LENGTH_LONG).show();
            }else{
                try {
                    model.startEarOfGod(dto);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(compoundButton.getId()==R.id.swt_active ){
            DtoRootDetailOfCar dto=(DtoRootDetailOfCar)compoundButton.getTag();
            if(b){
                model.startMovementSensor(dto);
            }else{

            }

        }else if(compoundButton.getId()==R.id.swt_active_geo){
            DtoRootDetailOfCar dto=(DtoRootDetailOfCar)compoundButton.getTag();
            if(b){
                model.startGeolocation(dto);
            }else{

            }
        }

    }

    public void onClickExpanNavigation(View v){

//        if(!drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START);
//        }else{
//            drawer_layout.openDrawer(GravityCompat.END);
//        }

    }
}
