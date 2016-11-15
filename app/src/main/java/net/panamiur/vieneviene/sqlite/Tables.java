package net.panamiur.vieneviene.sqlite;

/**
 * Created by gnu on 9/11/16.
 */

public class Tables {

    public final String TABLE_CAR="CREATE TABLE car(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "reg_id TEXT,"+
            "hash_device TEXT," +
            "color TEXT," +
            "description TEXT," +
            "phone_number TEXT," +
            "date_create TEXT)";

    public final String TABLE_CAR_DETAIL="CREATE TABLE car_detail(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "hash_car TEXT," +
            "battery INTEGER," +
            "lon TEXT," +
            "lat TEXT," +
            "date_capture TEXT";
}
