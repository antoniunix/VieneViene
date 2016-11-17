package net.panamiur.vieneviene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * @author  antoniunix
 * Esta clase se muestra solo en el ROLEDEVICE ROOT, ya que se desplegara el codigo QR con la informacion
 * del REG ID FCM y hash del dispocitivo
 */
public class HomeMap extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_map);
        init();
    }

    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);
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
}
