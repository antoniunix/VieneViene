package net.panamiur.vieneviene.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.panamiur.vieneviene.dto.DtoWtdDetailDeviceToReport;
import net.panamiur.vieneviene.sqlite.AppDb;

/**
 * Created by gnu on 17/11/16.
 */

public class DaoWtdDetailDeviceToReport {
    private AppDb helper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME="wtd_detail_device_to_report";
    public static String PK_FIELD="_id";

    private final String ID="_id";
    private final String HASH_DEVICE="hash_device";
    private final String REG_ID="reg_id";

    public DaoWtdDetailDeviceToReport(Context context) {
        helper=new AppDb(context);
    }

    /**
     * Insert
     */
    public int insert( DtoWtdDetailDeviceToReport obj)
    {
        db = helper.getWritableDatabase();
        int resp=0;
        try {

            SQLiteStatement insStmnt=db.compileStatement("" +
                    "INSERT INTO " +
                    TABLE_NAME+" ("+HASH_DEVICE+","+REG_ID+") VALUES(?,?);");
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

    public DtoWtdDetailDeviceToReport select()
    {

        db=helper.getWritableDatabase();
        cursor=db.rawQuery("SELECT hash_device,reg_id FROM "+TABLE_NAME,null);
        DtoWtdDetailDeviceToReport dto=new DtoWtdDetailDeviceToReport();

        if(cursor.moveToFirst())
        {
            dto.setHashDevice(cursor.getString(cursor.getColumnIndexOrThrow("hash_device")));
            dto.setRegId(cursor.getString(cursor.getColumnIndexOrThrow("reg_id")));
        }
        cursor.close();
        db.close();
        return dto;
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
