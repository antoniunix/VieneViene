package net.panamiur.vieneviene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.panamiur.vieneviene.dto.DtoRootDetailOfCar;
import net.panamiur.vieneviene.dto.DtoRootDetailOfCarTemp;
import net.panamiur.vieneviene.model.ModelDialogCompleteRegister;

public class DialogCompleteRegister extends AppCompatActivity {

    private ModelDialogCompleteRegister model;
    private TextView txt_device_model;
    private TextView edt_name_of_car;
    private TextView edt_phone_number;
    private TextView edt_description;
    private DtoRootDetailOfCarTemp dtoRootDetailOfCarTemp;
    private DtoRootDetailOfCar dtoRootDetailOfCar;

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
        edt_description=(EditText)findViewById(R.id.edt_description);
        model=new ModelDialogCompleteRegister(this);
        dtoRootDetailOfCarTemp=model.getCarDetailTemp();
        dtoRootDetailOfCar=new DtoRootDetailOfCar();
        txt_device_model.setText(dtoRootDetailOfCarTemp.getModelDevice());
    }

    public void onClickSave(View v){

        dtoRootDetailOfCar.setHashDevice(dtoRootDetailOfCarTemp.getHashDevice())
                .setRegId(dtoRootDetailOfCarTemp.getRegId())
                .setNameCar(edt_name_of_car.getText().toString())
                .setColor("")
                .setPhoneNumber(edt_phone_number.getText().toString())
                .setDescription(edt_description.getText().toString())
                .setModelDevice(dtoRootDetailOfCarTemp.getModelDevice())
                .setDateCreate(System.currentTimeMillis()+"");
        model.registerDevice(dtoRootDetailOfCar);
        finish();
    }
}
