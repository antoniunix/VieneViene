package net.panamiur.vieneviene.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.panamiur.vieneviene.dto.DtoRootDetailOfCarTemp;
import net.panamiur.vieneviene.dto.DtoWtdDetailDeviceToReport;
import net.panamiur.vieneviene.sqlite.AppDb;

/**
 * Created by gnu on 17/11/16.
 */

public class DaoRootDetailOfCarTemp {

    private AppDb helper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME="root_detail_of_car_temp";
    public static String PK_FIELD="_id";

    private final String ID="_id";
    private final String HASH_DEVICE="hash_device";
    private final String REG_ID="reg_id";
    private final String DATE_RECEIVED="date_received";
    private final String MODEL_DEVICE="model_device";

    public DaoRootDetailOfCarTemp(Context context) {
        helper=new AppDb(context);
    }

    /**
     * Insert
     */
    public int insert( DtoRootDetailOfCarTemp obj)
    {
        db = helper.getWritableDatabase();
        int resp=0;
        try {

            SQLiteStatement insStmnt=db.compileStatement("" +
                    "INSERT INTO " +
                    TABLE_NAME+" ("+HASH_DEVICE+","+REG_ID+","+DATE_RECEIVED+","+MODEL_DEVICE+") VALUES(?,?,?,?);");
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
                insStmnt.bindString(3, obj.getDateReceived());
            } catch (Exception e) {
                insStmnt.bindNull(3);
            }
            try {
                insStmnt.bindString(4, obj.getModelDevice());
            } catch (Exception e) {
                insStmnt.bindNull(4);
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

    public DtoRootDetailOfCarTemp select()
    {

        db=helper.getWritableDatabase();
        cursor=db.rawQuery("select " +
                "root_detail_of_car_temp._id," +
                "root_detail_of_car_temp.hash_device," +
                "root_detail_of_car_temp.reg_id," +
                "root_detail_of_car_temp.model_device," +
                "root_detail_of_car_temp.date_received " +
                "from root_detail_of_car_temp",null);
        DtoRootDetailOfCarTemp dto=new DtoRootDetailOfCarTemp();

        if(cursor.moveToFirst())
        {
            dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            dto.setHashDevice(cursor.getString(cursor.getColumnIndexOrThrow("hash_device")));
            dto.setRegId(cursor.getString(cursor.getColumnIndexOrThrow("reg_id")));
            dto.setModelDevice(cursor.getString(cursor.getColumnIndexOrThrow("model_device")));
            dto.setDateReceived(cursor.getString(cursor.getColumnIndexOrThrow("date_received")));
        }
        cursor.close();
        db.close();
        return dto;
    }


    public int delete(String hashDevice)
    {
        int resp=0;
        try {
            db = helper.getWritableDatabase();
            resp = db.delete(TABLE_NAME, "hash_device='"+hashDevice+"'", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            db.close();
        }
        return resp;
    }
}
