package net.panamiur.vieneviene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import net.panamiur.vieneviene.dto.DtoRootDetailOfCar;
import net.panamiur.vieneviene.dto.DtoRootDetailOfCarTemp;
import net.panamiur.vieneviene.model.ModelDialogCompleteRegister;
import net.panamiur.vieneviene.util.Config;

public class DialogCompleteRegister extends AppCompatActivity {

    private ModelDialogCompleteRegister model;
    private TextView txt_device_model;
    private TextView edt_name_of_car;
    private TextView edt_phone_number;
    private TextView edt_description;
    private DtoRootDetailOfCarTemp dtoRootDetailOfCarTemp;
    private DtoRootDetailOfCar dtoRootDetailOfCar;
    private SeekBar seekbar_sensibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_complete_register);
        init();
    }

    private void init(){

        txt_device_model=(TextView)findViewById(R.id.txt_device_model);
        edt_name_of_car=(EditText)findViewById(R.id.edt_name_of_car);
        edt_phone_number=(EditText)findViewById(R.id.edt_phone_number);
        seekbar_sensibility=(SeekBar)findViewById(R.id.seekbar_sensibility);
        edt_description=(EditText)findViewById(R.id.edt_description);
        model=new ModelDialogCompleteRegister(this);


        dtoRootDetailOfCarTemp=model.getCarDetailTemp();

        if(!getIntent().hasExtra(Config.NAME_SHARE_PREFERENCE)){
            dtoRootDetailOfCar=new DtoRootDetailOfCar();
            dtoRootDetailOfCar.setHashDevice(dtoRootDetailOfCarTemp.getHashDevice())
                    .setModelDevice(dtoRootDetailOfCarTemp.getModelDevice())
                    .setRegId(dtoRootDetailOfCarTemp.getRegId())
                    .setDateCreate(System.currentTimeMillis()+"");
            txt_device_model.setText(dtoRootDetailOfCarTemp.getModelDevice());
        }else{
            dtoRootDetailOfCar=(DtoRootDetailOfCar)getIntent().getExtras().get(Config.NAME_SHARE_PREFERENCE);
            edt_name_of_car.setText(dtoRootDetailOfCar.getNameCar());
            edt_phone_number.setText(dtoRootDetailOfCar.getPhoneNumber());
            edt_description.setText(dtoRootDetailOfCar.getDescription());
            txt_device_model.setText(dtoRootDetailOfCar.getModelDevice());
        }


    }

    public void onClickSave(View v){

        dtoRootDetailOfCar.setNameCar(edt_name_of_car.getText().toString())
                .setColor("")
                .setPhoneNumber(edt_phone_number.getText().toString())
                .setDescription(edt_description.getText().toString());

               if((seekbar_sensibility.getProgress()/2f)==0){
                   dtoRootDetailOfCar.setSensitiveMovement(2);
               }else if((seekbar_sensibility.getProgress()/2f)==0.5){
                   dtoRootDetailOfCar.setSensitiveMovement(1.5f);
               }else if((seekbar_sensibility.getProgress()/2f)==1.0){
                   dtoRootDetailOfCar.setSensitiveMovement(1.0f);
               }else if((seekbar_sensibility.getProgress()/2f)==1.5){
                   dtoRootDetailOfCar.setSensitiveMovement(0.5f);
               }


        model.registerDevice(dtoRootDetailOfCar);
        finish();
    }

}
