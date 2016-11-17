package net.panamiur.vieneviene.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.panamiur.vieneviene.dto.DtoRegisterDeviceQrGen;
import net.panamiur.vieneviene.sqlite.AppDb;

/**
 * Created by gnu on 16/11/16.
 */

public class DaoDeviceRoleRootDetail {

    private AppDb helper;
    private SQLiteDatabase db;

    public static String TABLE_NAME="device_role_root_detail";
    public static String PK_FIELD="_id";

    private final String ID="_id";
    private final String HASH_DEVICE="hash_device";
    private final String REG_ID="reg_id";

    public DaoDeviceRoleRootDetail(Context context) {
        helper=new AppDb(context);
    }

    /**
     * Insert
     */
    public int insert( DtoRegisterDeviceQrGen obj)
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
