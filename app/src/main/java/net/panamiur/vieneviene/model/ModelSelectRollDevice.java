package net.panamiur.vieneviene.model;

import android.content.Context;
import android.content.SharedPreferences;

import net.panamiur.vieneviene.network.SendPush;
import net.panamiur.vieneviene.util.Config;

/**
 * Created by gnu on 8/11/16.
 */

public class ModelSelectRollDevice {

    private Context context;

    public ModelSelectRollDevice(Context context) {
        this.context=context;
    }

    public void setRoleToSharePreference(int bRole){
        SharedPreferences sharedPref = context.getSharedPreferences(Config.NAME_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(Config.ITEM_SHP_ROLE, bRole);
        editor.commit();


    }
}
