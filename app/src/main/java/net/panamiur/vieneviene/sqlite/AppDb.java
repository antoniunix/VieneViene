package net.panamiur.vieneviene.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import net.panamiur.vieneviene.util.Config;

/**
 * Created by gnu on 9/11/16.
 */

public class AppDb extends SQLiteOpenHelper {

    private Tables tablas;

    public AppDb(Context context) {
        super(context, Environment.getExternalStorageDirectory()+"/VIENEVIENE/"+Config.DB_NAME, null, Config.VERSION_DB);
        tablas = new Tables();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablas.TABLE_WTD_DETAIL_DEVICE_TO_REPORT);
        db.execSQL(tablas.TABLE_ROOT_DETAIL_OF_CAR);
        db.execSQL(tablas.TABLE_ROOT_LAST_REPORTS_OF_CAR);
        db.execSQL(tablas.TABLE_ROOT_DETAIL_OF_CAR_TEMP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:

            default:
                break;
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
