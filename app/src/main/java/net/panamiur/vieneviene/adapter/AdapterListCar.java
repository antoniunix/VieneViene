package net.panamiur.vieneviene.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import net.panamiur.vieneviene.R;
import net.panamiur.vieneviene.dao.DaoRootDetailOfCar;
import net.panamiur.vieneviene.dto.DtoRootDetailOfCar;
import net.panamiur.vieneviene.util.Config;

import java.util.List;

/**
 * Created by gnu on 23/11/16.
 */

public class AdapterListCar extends ArrayAdapter<DtoRootDetailOfCar> {

    private LayoutInflater layoutInflater;
    private View.OnClickListener onClickListener;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private Context context;


    public AdapterListCar(Context context, List<DtoRootDetailOfCar> list, View.OnClickListener onClickListener,
                          CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        super(context, R.layout.row_detail_car, list);
        this.context = context;
        this.onClickListener = onClickListener;
        this.onCheckedChangeListener = onCheckedChangeListener;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public DtoRootDetailOfCar getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        TextView txt_battery, txt_speed;
        ImageButton btn_config, btn_ear_of_good;
        TextView txt_name, txt_model, txt_description;
        Switch swt_active, swt_active_geo;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.row_detail_car, null);

            txt_battery = (TextView) view.findViewById(R.id.txt_battery);
            txt_speed = (TextView) view.findViewById(R.id.txt_speed);
            btn_config = (ImageButton) view.findViewById(R.id.btn_config);
            btn_ear_of_good = (ImageButton) view.findViewById(R.id.btn_ear_of_good);
            txt_name = (TextView) view.findViewById(R.id.txt_name);
            txt_model = (TextView) view.findViewById(R.id.txt_model);
            txt_description = (TextView) view.findViewById(R.id.txt_description);
            swt_active = (Switch) view.findViewById(R.id.swt_active);
            swt_active_geo = (Switch) view.findViewById(R.id.swt_active_geo);

            view.setTag(new ViewHolder(txt_battery, btn_config, txt_name, txt_model, txt_description, swt_active, btn_ear_of_good, swt_active_geo, txt_speed));
        } else {
            ViewHolder vh = (ViewHolder) view.getTag();
            txt_battery = vh.txt_battery;
            btn_config = vh.btn_config;
            btn_ear_of_good = vh.btn_ear_of_good;
            txt_name = vh.txt_name;
            txt_model = vh.txt_model;
            txt_description = vh.txt_description;
            swt_active = vh.swt_active;
            swt_active_geo = vh.swt_active_geo;
            txt_speed = vh.txt_speed;
        }

        DtoRootDetailOfCar dto = getItem(position);
        btn_config.setTag(dto);
        btn_ear_of_good.setTag(dto);
        swt_active.setTag(dto);
        swt_active_geo.setTag(dto);
        swt_active.setOnCheckedChangeListener(onCheckedChangeListener);
        swt_active_geo.setOnCheckedChangeListener(onCheckedChangeListener);
        btn_config.setOnClickListener(onClickListener);
        btn_ear_of_good.setOnClickListener(onClickListener);

        swt_active_geo.setChecked(isActiveGeolocation(dto.getHashDevice()));
        swt_active.setChecked(isActiveMovement(dto.getHashDevice()));

        if (swt_active_geo.isChecked()) {
            txt_battery.setText(dto.getBatteryLevel() + "%");
            txt_speed.setText(dto.getSpeed() + "Km");
        }else{
            txt_battery.setText("NA");
            txt_speed.setText("NA");
        }

        txt_name.setText(dto.getNameCar());
        txt_model.setText(dto.getModelDevice());
        txt_description.setText(dto.getDescription());


        return view;
    }

    class ViewHolder {
        TextView txt_battery;
        ImageButton btn_config, btn_ear_of_good;
        TextView txt_name, txt_model, txt_description,txt_speed;
        Switch swt_active, swt_active_geo;

        public ViewHolder(TextView txt_battery, ImageButton btn_config, TextView txt_name,
                          TextView txt_model, TextView txt_description, Switch swt_active,
                          ImageButton btn_ear_of_good, Switch swt_active_geo,TextView txt_speed) {

            this.txt_battery = txt_battery;
            this.btn_config = btn_config;
            this.txt_name = txt_name;
            this.txt_model = txt_model;
            this.txt_description = txt_description;
            this.swt_active = swt_active;
            this.btn_ear_of_good = btn_ear_of_good;
            this.swt_active_geo = swt_active_geo;
            this.txt_speed=txt_speed;
        }
    }

    private boolean isActiveGeolocation(String hashOfCar) {
        return new DaoRootDetailOfCar(context).selectByHash(hashOfCar).isGeoActived();
    }

    private boolean isActiveMovement(String hashOfCar) {
        return new DaoRootDetailOfCar(context).selectByHash(hashOfCar).isMovementActived();
    }


}
