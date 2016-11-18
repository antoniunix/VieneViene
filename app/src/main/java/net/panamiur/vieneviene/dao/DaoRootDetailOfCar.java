package net.panamiur.vieneviene.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.panamiur.vieneviene.dto.DtoRootDetailOfCar;
import net.panamiur.vieneviene.sqlite.AppDb;

/**
 * Created by gnu on 17/11/16.
 */

public class DaoRootDetailOfCar {
    private AppDb helper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME="root_detail_of_car";
    public static String PK_FIELD="_id";

    private final String ID="_id";
    private final String HASH_DEVICE="hash_device";
    private final String REG_ID="reg_id";
    private final String NAME_CAR="name_car";
    private final String COLOR="color";
    private final String PHONE_NUMBER="phone_number";
    private final String DESCRIPTION="description";
    private final String DATE_CREATE="date_create";

    public DaoRootDetailOfCar(Context context) {
        helper=new AppDb(context);
    }

    /**
     * Insert
     */
    public int insert( DtoRootDetailOfCar obj)
    {
        db = helper.getWritableDatabase();
        int resp=0;
        try {

            SQLiteStatement insStmnt=db.compileStatement("" +
                    "INSERT INTO " +
                    TABLE_NAME+" ("+HASH_DEVICE+","+REG_ID+","+NAME_CAR+","+COLOR+","+PHONE_NUMBER+","+DESCRIPTION+","+DATE_CREATE+") VALUES(?,?,?,?,?,?,?);");
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
