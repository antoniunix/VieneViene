package net.panamiur.vieneviene.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import net.panamiur.vieneviene.dto.DtoRootDetailOfCar;
import net.panamiur.vieneviene.dto.DtoRootDetailOfCarTemp;
import net.panamiur.vieneviene.sqlite.AppDb;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gnu on 17/11/16.
 */

public class DaoRootDetailOfCar {
    private AppDb helper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "root_detail_of_car";
    public static String PK_FIELD = "_id";

    private final String ID = "_id";
    private final String HASH_DEVICE = "hash_device";
    private final String REG_ID = "reg_id";
    private final String NAME_CAR = "name_car";
    private final String COLOR = "color";
    private final String PHONE_NUMBER = "phone_number";
    private final String DESCRIPTION = "description";
    private final String DATE_CREATE = "date_create";
    private final String MODEL_DEVICE = "model_device";
    private final String SENSITIVE_MOVEMENT = "sensitive_movement";
    private final String IS_GEO_ACTIVED = "is_geo_active";
    private final String IS_MOVEMENT_ACTIVED = "is_movement_active";

    public DaoRootDetailOfCar(Context context) {
        helper = new AppDb(context);
    }

    /**
     * Insert
     */
    public int insert(DtoRootDetailOfCar obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {

            SQLiteStatement insStmnt = db.compileStatement("" +
                    "INSERT INTO " +
                    TABLE_NAME + " (" + HASH_DEVICE + "," + REG_ID + "," + NAME_CAR + "," + COLOR
                                + "," + PHONE_NUMBER + "," + DESCRIPTION + "," + DATE_CREATE + ","
                                + MODEL_DEVICE +","+SENSITIVE_MOVEMENT+","+IS_GEO_ACTIVED+","+IS_MOVEMENT_ACTIVED+ ") VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            db.beginTransaction();
            try {
                insStmnt.bindString(1, obj.getHashDevice());
            } catch (Exception e) {
                insStmnt.bindNull(1);
            }
            try {
                insStmnt.bindString(2, obj.getRegId());
            } catch (Exception e) {
                insStmnt.bindNull(2);
            }
            try {
                insStmnt.bindString(3, obj.getNameCar());
            } catch (Exception e) {
                insStmnt.bindNull(3);
            }
            try {
                insStmnt.bindString(4, obj.getColor());
            } catch (Exception e) {
                insStmnt.bindNull(4);
            }
            try {
                insStmnt.bindString(5, obj.getPhoneNumber());
            } catch (Exception e) {
                insStmnt.bindNull(5);
            }
            try {
                insStmnt.bindString(6, obj.getDescription());
            } catch (Exception e) {
                insStmnt.bindNull(6);
            }
            try {
                insStmnt.bindString(7, obj.getDateCreate());
            } catch (Exception e) {
                insStmnt.bindNull(7);
            }
            try {
                insStmnt.bindString(8, obj.getModelDevice());
            } catch (Exception e) {
                insStmnt.bindNull(8);
            }
            try {
                insStmnt.bindDouble(9, obj.getSensitiveMovement());
            } catch (Exception e) {
                insStmnt.bindNull(9);
            }
            try {
                insStmnt.bindLong(10, obj.isGeoActived()?1:0);
            } catch (Exception e) {
                insStmnt.bindNull(10);
            }
            try {
                insStmnt.bindLong(11, obj.isMovementActived()?1:0);
            } catch (Exception e) {
                insStmnt.bindNull(11);
            }
            insStmnt.executeInsert();
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println("error db");
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        db.close();
        return resp;
    }

    public int update(DtoRootDetailOfCar dto) {

        db = helper.getWritableDatabase();
        int isUpdate=0;
        try {

            ContentValues values = new ContentValues();
            values.put(NAME_CAR, dto.getNameCar());
            values.put(COLOR, dto.getColor());
            values.put(PHONE_NUMBER, dto.getPhoneNumber());
            values.put(DESCRIPTION, dto.getDescription());
            values.put(SENSITIVE_MOVEMENT, dto.getSensitiveMovement());

            isUpdate = db.update(TABLE_NAME,
                    values,
                    HASH_DEVICE + "='" + dto.getHashDevice()+"'", null);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        return isUpdate;
    }


    public List<DtoRootDetailOfCar> select() {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT DISTINCT\n" +
                "root_detail_of_car._id,\n" +
                "root_detail_of_car.hash_device,\n" +
                "root_detail_of_car.reg_id,\n" +
                "root_detail_of_car.name_car,\n" +
                "root_detail_of_car.color,\n" +
                "root_detail_of_car.phone_number,\n" +
                "root_detail_of_car.description,\n" +
                "root_detail_of_car.model_device,\n" +
                "root_detail_of_car.date_create,\n" +
                "root_detail_of_car.sensitive_movement,\n" +
                "root_last_reports_of_car.battery,\n" +
                "root_last_reports_of_car.speed,\n" +
                "root_detail_of_car.is_geo_active,\n" +
                "root_detail_of_car.is_movement_active\n" +
                "FROM\n" +
                "root_detail_of_car\n" +
                "LEFT JOIN root_last_reports_of_car ON root_last_reports_of_car.hash_device=root_detail_of_car.hash_device\n" +
                "GROUP BY root_detail_of_car.hash_device\n" +
                "ORDER BY root_detail_of_car._id", null);

        List<DtoRootDetailOfCar> lst = new ArrayList<>(cursor.getCount());
        DtoRootDetailOfCar dto;

        int numColum_id = cursor.getColumnIndexOrThrow("_id");
        int numColum_hash_device = cursor.getColumnIndexOrThrow("hash_device");
        int numColum_reg_id = cursor.getColumnIndexOrThrow("reg_id");
        int numColum_name_car = cursor.getColumnIndexOrThrow("name_car");
        int numColum_color = cursor.getColumnIndexOrThrow("color");
        int numColum_phone_number = cursor.getColumnIndexOrThrow("phone_number");
        int numColum_description = cursor.getColumnIndexOrThrow("description");
        int numColum_model_device = cursor.getColumnIndexOrThrow("model_device");
        int numColum_date_create = cursor.getColumnIndexOrThrow("date_create");
        int numColum_battery = cursor.getColumnIndexOrThrow("battery");
        int numColum_speed = cursor.getColumnIndexOrThrow("speed");
        int numColum_sensitive_movement = cursor.getColumnIndexOrThrow("sensitive_movement");
        int numColum_isGeoActived = cursor.getColumnIndexOrThrow("is_geo_active");
        int numColum_isMovementActived = cursor.getColumnIndexOrThrow("is_movement_active");

        if (cursor.moveToFirst()) {
            do {
                dto = new DtoRootDetailOfCar();
                dto.setId(cursor.getInt(numColum_id));
                dto.setHashDevice(cursor.getString(numColum_hash_device));
                dto.setRegId(cursor.getString(numColum_reg_id));
                dto.setNameCar(cursor.getString(numColum_name_car));
                dto.setColor(cursor.getString(numColum_color));
                dto.setPhoneNumber(cursor.getString(numColum_phone_number));
                dto.setDescription(cursor.getString(numColum_description));
                dto.setModelDevice(cursor.getString(numColum_model_device));
                dto.setBatteryLevel(cursor.getLong(numColum_battery));
                dto.setSpeed(cursor.getFloat(numColum_speed));
                dto.setSensitiveMovement(cursor.getLong(numColum_sensitive_movement));
                dto.setDateCreate(cursor.getString(numColum_date_create));
                dto.setGeoActived(cursor.getInt(numColum_isGeoActived)==1);
                dto.setMovementActived(cursor.getInt(numColum_isMovementActived)==1);
                lst.add(dto);

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return lst;
    }

    public DtoRootDetailOfCar selectByHash(String hashOfCar) {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT DISTINCT\n" +
                "root_detail_of_car._id,\n" +
                "root_detail_of_car.hash_device,\n" +
                "root_detail_of_car.reg_id,\n" +
                "root_detail_of_car.name_car,\n" +
                "root_detail_of_car.color,\n" +
                "root_detail_of_car.phone_number,\n" +
                "root_detail_of_car.description,\n" +
                "root_detail_of_car.model_device,\n" +
                "root_detail_of_car.date_create,\n" +
                "root_detail_of_car.sensitive_movement,\n" +
                "root_detail_of_car.is_geo_active,\n" +
                "root_detail_of_car.is_movement_active\n" +
                "FROM\n" +
                "root_detail_of_car\n" +
                "WHERE root_detail_of_car.hash_device='"+hashOfCar+"'", null);

        DtoRootDetailOfCar dto= new DtoRootDetailOfCar();;

        int numColum_id = cursor.getColumnIndexOrThrow("_id");
        int numColum_hash_device = cursor.getColumnIndexOrThrow("hash_device");
        int numColum_reg_id = cursor.getColumnIndexOrThrow("reg_id");
        int numColum_name_car = cursor.getColumnIndexOrThrow("name_car");
        int numColum_color = cursor.getColumnIndexOrThrow("color");
        int numColum_phone_number = cursor.getColumnIndexOrThrow("phone_number");
        int numColum_description = cursor.getColumnIndexOrThrow("description");
        int numColum_model_device = cursor.getColumnIndexOrThrow("model_device");
        int numColum_date_create = cursor.getColumnIndexOrThrow("date_create");
        int numColum_sensitive_movement = cursor.getColumnIndexOrThrow("sensitive_movement");
        int numColum_isGeoActived = cursor.getColumnIndexOrThrow("is_geo_active");
        int numColum_isMovementActived = cursor.getColumnIndexOrThrow("is_movement_active");

        if (cursor.moveToFirst()) {
                dto.setId(cursor.getInt(numColum_id));
                dto.setHashDevice(cursor.getString(numColum_hash_device));
                dto.setRegId(cursor.getString(numColum_reg_id));
                dto.setNameCar(cursor.getString(numColum_name_car));
                dto.setColor(cursor.getString(numColum_color));
                dto.setPhoneNumber(cursor.getString(numColum_phone_number));
                dto.setDescription(cursor.getString(numColum_description));
                dto.setModelDevice(cursor.getString(numColum_model_device));
                dto.setSensitiveMovement(cursor.getLong(numColum_sensitive_movement));
                dto.setDateCreate(cursor.getString(numColum_date_create));
                dto.setGeoActived(cursor.getInt(numColum_isGeoActived)==1);
                dto.setMovementActived(cursor.getInt(numColum_isMovementActived)==1);

        }
        cursor.close();
        db.close();
        return dto;
    }


    public int changeStatusGeo(DtoRootDetailOfCar dto,boolean isActive) {

        db = helper.getWritableDatabase();
        int isUpdate=0;
        try {

            ContentValues values = new ContentValues();
            values.put(IS_GEO_ACTIVED, isActive?1:0);
            isUpdate = db.update(TABLE_NAME,
                    values,
                    HASH_DEVICE + "='" + dto.getHashDevice()+"'", null);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        return isUpdate;
    }
    public int changeStatusMovement(DtoRootDetailOfCar dto,boolean isActive) {

        db = helper.getWritableDatabase();
        int isUpdate=0;
        try {

            ContentValues values = new ContentValues();
            values.put(IS_MOVEMENT_ACTIVED, isActive?1:0);
            isUpdate = db.update(TABLE_NAME,
                    values,
                    HASH_DEVICE + "='" + dto.getHashDevice()+"'", null);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        return isUpdate;
    }


    public int delete(String hashDevice) {
        int resp = 0;
        try {
            db = helper.getWritableDatabase();
            resp = db.delete(TABLE_NAME, "hash_device='" + hashDevice + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return resp;
    }

}
