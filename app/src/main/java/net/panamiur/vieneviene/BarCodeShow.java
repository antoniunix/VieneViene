package net.panamiur.vieneviene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import net.panamiur.vieneviene.model.ModelBarCodeShow;

public class BarCodeShow extends AppCompatActivity {

    private ImageView img_qr_wd;
    private ModelBarCodeShow model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_show);
        init();
    }

    private void init(){
        model=new ModelBarCodeShow(this);
        img_qr_wd=(ImageView)findViewById(R.id.img_qr_wd);

        try {
            img_qr_wd.setImageBitmap(model.getQrOfText());
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void onClickContinue(View v){
        finish();
    }
}
