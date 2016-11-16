package net.panamiur.vieneviene;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.WriterException;

import net.panamiur.readwriteqr.barcode.BarcodeCaptureActivity;
import net.panamiur.vieneviene.DialogFragments.DialogWarningMessageUser;
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

    private final int DESERIALIZE_OK=1;
    private final int DESERIALIZE_MALFORMED=2;
    private final int DESERIALIZE_IQUALS_ROLE=3;


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
                    switch (model.validateQRCode(barcode.displayValue,iRole)){
                        case DESERIALIZE_OK:

                            break;
                        case DESERIALIZE_MALFORMED:
                            new AlertDialog.Builder(this)
                                    .setTitle(getResources().getString(R.string.title_msg_warning_scanned_bad_qr))
                                    .setMessage(getResources().getString(R.string.msg_warning_scanned_bad_qr))
                                    .setNeutralButton(R.string.button_dialog_warning_message_user_next, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                            break;
                        case DESERIALIZE_IQUALS_ROLE:
                            new AlertDialog.Builder(this)
                                    .setTitle(getResources().getString(R.string.title_msg_warning_equals_role))
                                    .setMessage(iRole==Config.ROL_ROOT?getResources().getString(R.string.msg_warning_eq_role_root):getResources().getString(R.string.msg_warning_eq_role_watchdog))
                                    .setNeutralButton(R.string.button_dialog_warning_message_user_next, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                            break;
                        default:
                            break;
                    }

                } else {
                    new AlertDialog.Builder(this)
                            .setTitle(getResources().getString(R.string.title_msg_error_scanned_qr))
                            .setMessage(getResources().getString(R.string.msg_error_scanned_qr))
                            .setNeutralButton(R.string.button_dialog_warning_message_user_next, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            } else{
            Log.e(LOG_TAG,CommonStatusCodes.getStatusCodeString(resultCode));

            }
        } else super.onActivityResult(requestCode, resultCode, data);
    }
}
