package net.panamiur.vieneviene;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.stream.MalformedJsonException;

import net.panamiur.movementreport.Movement;
import net.panamiur.readwriteqr.barcode.BarcodeCaptureActivity;
import net.panamiur.vieneviene.model.ModelScanQr;

import java.io.UnsupportedEncodingException;

public class ScanQR extends AppCompatActivity {

    private ModelScanQr model;
    private static final int BARCODE_READER_REQUEST_CODE = 1;
    private final String LOG_TAG = "ScanQR";
    private Button btn_scann_qr_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_qr);
        init();
    }

    private void init() {
        btn_scann_qr_save=(Button)findViewById(R.id.btn_scann_qr_save);
        model = new ModelScanQr(this);
    }

    public void onClickCaptureQr(View v) {
        Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
        startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);
    }

    public void onClickCaptureQrSave(View v) {
        try {
            model.sendRegistryFCM();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        startActivity(new Intent(this, WatchDog.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BARCODE_READER_REQUEST_CODE && resultCode == CommonStatusCodes.SUCCESS && data != null) {
            Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
            if (model.validateQRCode(barcode.displayValue)) {
                try {
                    model.registerDeviceRootInDB(model.deserializeTextQR(barcode.displayValue));
                    btn_scann_qr_save.setVisibility(View.VISIBLE);

                } catch (MalformedJsonException e) {
                    e.printStackTrace();
                }
            } else {
                new AlertDialog.Builder(this)
                        .setTitle(getResources().getString(R.string.title_msg_warning_scanned_bad_qr))
                        .setMessage(getResources().getString(R.string.msg_error_scanned_qr))
                        .setNeutralButton(R.string.button_bcs_continue, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.title_msg_error_scanned_qr))
                    .setMessage(getResources().getString(R.string.msg_error_scanned_qr))
                    .setNeutralButton(R.string.button_bcs_continue, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
