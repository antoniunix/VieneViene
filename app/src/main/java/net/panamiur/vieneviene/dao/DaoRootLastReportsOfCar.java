package net.panamiur.vieneviene.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.panamiur.vieneviene.dto.DtoRootDetailOfCar;
import net.panamiur.vieneviene.dto.DtoRootLastReportsOfCar;
import net.panamiur.vieneviene.sqlite.AppDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gnu on 17/11/16.
 */

public class DaoRootLastReportsOfCar {
    private AppDb helper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME="root_last_reports_of_car";
    public static String PK_FIELD="_id";

    private final String ID="_id";
    private final String HASH_DEVICE="hash_device";
    private final String BATTERY="battery";
    private final String LON="lon";
    private final String LAT="lat";
    private final String DATE_CAPTURE="date_capture";

    public DaoRootLastReportsOfCar(Context context) {
        helper=new AppDb(context);
    }

    /**
     * Insert
     */
    public int insert( DtoRootLastReportsOfCar obj)
    {
        db = helper.getWritableDatabase();
        int resp=0;
        try {

            SQLiteStatement insStmnt=db.compileStatement("" +
                    "INSERT INTO " +
                    TABLE_NAME+" ("+HASH_DEVICE+","+BATTERY+","+LON+","+LAT+","+DATE_CAPTURE+") VALUES(?,?,?,?,?);");
            db.beginTransaction();
            try {
                insStmnt.bindString(1, obj.getHashDevice());
            } catch (Exception e) {
                insStmnt.bindNull(1);
            }
            try {
                insStmnt.bindString(2, obj.getBattery());
            } catch (Exception e) {
                insStmnt.bindNull(2);
            }
            try {
                insStmnt.bindDouble(3, obj.getLon());
            } catch (Exception e) {
                insStmnt.bindNull(3);
            }
            try {
                insStmnt.bindDouble(4, obj.getLat());
            } catch (Exception e) {
                insStmnt.bindNull(4);
            }
            try {
                insStmnt.bindString(5, obj.getDateCapture());
            } catch (Exception e) {
                insStmnt.bindNull(5);
            }
            insStmnt.executeInsert();
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("error db");
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
        }
        db.close();
        return resp;
    }

    public List<DtoRootLastReportsOfCar> selectLastGeo() {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "root_last_reports_of_car._id,\n" +
                "root_last_reports_of_car.hash_device,\n" +
                "root_last_reports_of_car.battery,\n" +
                "root_last_reports_of_car.lon,\n" +
                "root_last_reports_of_car.lat,\n" +
                "Max(root_last_reports_of_car.date_capture) AS date_capture,\n" +
                "root_detail_of_car.name_car,\n" +
                "root_detail_of_car.color\n" +
                "FROM\n" +
                "root_last_reports_of_car\n" +
                "INNER JOIN root_detail_of_car ON root_detail_of_car.hash_device=root_last_reports_of_car.hash_device\n" +
                "GROUP BY root_last_reports_of_car.hash_device", null);

        List<DtoRootLastReportsOfCar> lst = new ArrayList<>(cursor.getCount());
        DtoRootLastReportsOfCar dto;

        int numColum_id = cursor.getColumnIndexOrThrow("_id");
        int numColum_hash_device = cursor.getColumnIndexOrThrow("hash_device");
        int numColum_battery = cursor.getColumnIndexOrThrow("battery");
        int numColum_lon = cursor.getColumnIndexOrThrow("lon");
        int numColum_lat = cursor.getColumnIndexOrThrow("lat");
        int numColum_date_capture = cursor.getColumnIndexOrThrow("date_capture");
        int numColum_name_car = cursor.getColumnIndexOrThrow("name_car");
        int numColum_color = cursor.getColumnIndexOrThrow("color");

        if (cursor.moveToFirst()) {
            do {
                dto = new DtoRootLastReportsOfCar();
                dto.setId(cursor.getInt(numColum_id));
                dto.setHashDevice(cursor.getString(numColum_hash_device));
                dto.setBattery(cursor.getString(numColum_battery));
                dto.setLon(cursor.getDouble(numColum_lon));
                dto.setLat(cursor.getDouble(numColum_lat));
                dto.setDateCapture(cursor.getString(numColum_date_capture));
                dto.setNameCar(cursor.getString(numColum_name_car));
                dto.setColor(cursor.getString(numColum_color));
                lst.add(dto);

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return lst;
    }


    public int delete()
    {
        int resp=0;
        try {
            db = helper.getWritableDatabase();
            resp = db.delete(TABLE_NAME, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            db.close();
        }
        return resp;
    }

}
