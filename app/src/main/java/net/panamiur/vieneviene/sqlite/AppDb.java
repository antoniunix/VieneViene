package net.panamiur.vieneviene.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.panamiur.vieneviene.util.Config;

/**
 * Created by gnu on 9/11/16.
 */

public class AppDb extends SQLiteOpenHelper {

    private Tables tablas;

    public AppDb(Context context) {
        super(context, Config.DB_NAME, null, Config.VERSION_DB);
        tablas = new Tables();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablas.TABLE_CAR);
        db.execSQL(tablas.TABLE_CAR_DETAIL);
        db.execSQL(tablas.TABLE_DEVICE_ROLE_ROOT_DETAIL);
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
