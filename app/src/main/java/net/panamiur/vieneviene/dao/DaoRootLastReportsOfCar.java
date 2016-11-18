package net.panamiur.vieneviene.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.panamiur.vieneviene.dto.DtoRootLastReportsOfCar;
import net.panamiur.vieneviene.sqlite.AppDb;

/**
 * Created by gnu on 17/11/16.
 */

public class DaoRootLastReportsOfCar {
    private AppDb helper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME="root_detail_of_car_temp";
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
