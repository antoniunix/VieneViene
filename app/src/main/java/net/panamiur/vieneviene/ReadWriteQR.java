package net.panamiur.vieneviene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.WriterException;

import net.panamiur.readwriteqr.barcode.BarcodeCaptureActivity;
import net.panamiur.vieneviene.model.ModelReadWriteQR;
import net.panamiur.vieneviene.util.Config;

public class ReadWriteQR extends AppCompatActivity {

    private ModelReadWriteQR model;
    private ImageView img_qr_wd;
    private TextView txt_title_code_qr;
    private TextView txt_title_scan_qr;
    private static final int BARCODE_READER_REQUEST_CODE = 1;
    private final String LOG_TAG="ReadWriteQR";
    private int iRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write_qr);
        init();
    }

    private void init(){
        iRole=getIntent().getIntExtra(Config.NAME_INTENT_EXTRAS,0);
        model=new ModelReadWriteQR(this);
        img_qr_wd=(ImageView)findViewById(R.id.img_qr_wd);
        txt_title_code_qr=(TextView)findViewById(R.id.txt_title_code_qr);
        txt_title_scan_qr=(TextView)findViewById(R.id.txt_title_scan_qr);

        switch (iRole){
            case Config.ROL_ROOT:
                txt_title_code_qr.setText(getResources().getString(R.string.dialog_read_write_qr_gen_code_root));
                txt_title_scan_qr.setText(getResources().getString(R.string.dialog_read_write_qr_scan_code_root));
                break;
            case Config.ROL_WATCH_DOG:
                txt_title_code_qr.setText(getResources().getString(R.string.dialog_read_write_qr_gen_code_watch_dog));
                txt_title_scan_qr.setText(getResources().getString(R.string.dialog_read_write_qr_scan_code_watch_dog));
                break;
        }

        try {
            img_qr_wd.setImageBitmap(model.getQrOfText());
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void onClickCaptureQr(View v){
        Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
        startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);
    }

    public void onClickCaptureQrSave(View v){
        switch (iRole){
            case Config.ROL_ROOT:
                startActivity(new Intent(this,HomeMap.class));
                finish();
                break;
            case Config.ROL_WATCH_DOG:
                startActivity(new Intent(this,WatchDog.class));
                finish();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);



//                    txt_qr.setText(barcode.displayValue);
                } else {

//                    txt_qr.setText(R.string.msg_error_no_barcode_captured);
                }
            } else{
            Log.e(LOG_TAG,CommonStatusCodes.getStatusCodeString(resultCode));

            }
        } else super.onActivityResult(requestCode, resultCode, data);
    }
}
