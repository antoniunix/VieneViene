package net.panamiur.vieneviene.model;

import android.content.Context;
import android.util.Log;

import net.panamiur.vieneviene.dao.DaoRootDetailOfCar;
import net.panamiur.vieneviene.dao.DaoRootDetailOfCarTemp;
import net.panamiur.vieneviene.dto.DtoRootDetailOfCar;
import net.panamiur.vieneviene.dto.DtoRootDetailOfCarTemp;

/**
 * Created by gnu on 18/11/16.
 */

public class ModelDialogCompleteRegister {

    private Context context;

    public ModelDialogCompleteRegister(Context context) {
        this.context=context;
    }

    public DtoRootDetailOfCarTemp getCarDetailTemp(){
        return new DaoRootDetailOfCarTemp(context).select();
    }

    /**
     * se registra la informacion del watchdog
     */
    public void registerDevice(DtoRootDetailOfCar dto){

        new DaoRootDetailOfCarTemp(context).delete(dto.getHashDevice());
        DaoRootDetailOfCar daoRootDetailOfCar=new DaoRootDetailOfCar(context);
        if(daoRootDetailOfCar.update(dto)==0){
            daoRootDetailOfCar.insert(dto);
        }
    }
}
