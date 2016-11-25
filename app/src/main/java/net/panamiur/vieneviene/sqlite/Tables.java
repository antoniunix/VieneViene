package net.panamiur.vieneviene.sqlite;

/**
 * Created by gnu on 9/11/16.
 */

public class Tables {

    public final String TABLE_WTD_DETAIL_DEVICE_TO_REPORT="CREATE TABLE wtd_detail_device_to_report(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "hash_device TEXT," +
            "reg_id TEXT)";

    public final String TABLE_ROOT_DETAIL_OF_CAR="CREATE TABLE root_detail_of_car(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "hash_device TEXT," +
            "reg_id TEXT,"+
            "name_car TEXT,"+
            "color TEXT," +
            "phone_number TEXT," +
            "description TEXT," +
            "model_device TEXT," +
            "date_create TEXT," +
            "sensitive_movement REAL)";

    public final String TABLE_ROOT_LAST_REPORTS_OF_CAR="CREATE TABLE root_last_reports_of_car(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "hash_device TEXT," +
            "battery INTEGER," +
            "lon TEXT," +
            "lat TEXT," +
            "date_capture TEXT)";

    public final String TABLE_ROOT_DETAIL_OF_CAR_TEMP="CREATE TABLE root_detail_of_car_temp(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "hash_device TEXT," +
            "reg_id TEXT," +
            "model_device TEXT," +
            "date_received TEXT)";
}
