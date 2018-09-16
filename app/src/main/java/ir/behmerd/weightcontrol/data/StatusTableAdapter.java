package ir.behmerd.weightcontrol.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Database STATUS table adapter class.
 */
public class StatusTableAdapter extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "STATUS";
    private static final String TAG = "STATUS_TABLE_ADAPTER";

    // Constructor
    public StatusTableAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Commands
    public boolean insert(StatusRecord status){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("CHK_DATE", status.getCheckDate());
        contentValues.put("HEIGHT", status.getHeight());
        contentValues.put("WEIGHT", status.getWeight());
        contentValues.put("BMI", status.getBmi());
        contentValues.put("BODY_STATUS", status.getBody_status());
        contentValues.put("DIFFERENCE", status.getDifference());
        contentValues.put("ACTIVITIES", status.getActivities());
        try {
            db.insert(TABLE_NAME, null, contentValues);
            db.close();
            return true;
        }catch (Exception e){
            Log.e(TAG, "Insert operation failed!", e);
        }
        return false;
    }

    public boolean update(StatusRecord status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("CHK_DATE", status.getCheckDate());
        contentValues.put("HEIGHT", status.getHeight());
        contentValues.put("WEIGHT", status.getWeight());
        contentValues.put("BMI", status.getBmi());
        contentValues.put("BODY_STATUS", status.getBody_status());
        contentValues.put("DIFFERENCE", status.getDifference());
        contentValues.put("ACTIVITIES", status.getActivities());
        try {
            db.update(TABLE_NAME, contentValues, "ID = ? ",
                    new String[]{Integer.toString(status.getId())});
            db.close();
            return true;
        }catch (Exception e){
            Log.e(TAG, "Update operation failed!", e);
        }
        return false;
    }

    public boolean delete(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_NAME, "ID = ? ", new String[]{Integer.toString(id)});
            db.close();
            return true;
        }catch (Exception e){
            Log.e(TAG, "Delete operation failed!", e);
        }
        return false;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    // Queries
    public StatusRecord getRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME +
                " WHERE ID = " + String.valueOf(id);

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            StatusRecord status = new StatusRecord();
            if (cursor != null && cursor.moveToFirst()){
                status.setId(cursor.getInt(0));
                status.setCheckDate(cursor.getString(1));
                status.setHeight(cursor.getInt(2));
                status.setWeight(cursor.getFloat(3));
                status.setBmi(cursor.getFloat(4));
                status.setBody_status(cursor.getInt(5));
                status.setDifference(cursor.getFloat(6));
                status.setActivities(cursor.getString(7));
                cursor.close();
            }
            return status;
        }catch (Exception e){
            Log.e(TAG, "getRecord query execution failed!", e);
        }
        return null;
    }

    public List<StatusRecord> getAllRecords(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        List<StatusRecord> statusList = new ArrayList<StatusRecord>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    StatusRecord status = new StatusRecord();
                    status.setId(cursor.getInt(0));
                    status.setCheckDate(cursor.getString(1));
                    status.setHeight(cursor.getInt(2));
                    status.setWeight(cursor.getFloat(3));
                    status.setBmi(cursor.getFloat(4));
                    status.setBody_status(cursor.getInt(5));
                    status.setDifference(cursor.getFloat(6));
                    status.setActivities(cursor.getString(7));

                    statusList.add(status);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return statusList;
        }catch (Exception e){
            Log.e(TAG, "getAllRecords query execution failed!", e);
        }
        return null;
    }

    public List<StatusRecord> getLast5(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID DESC LIMIT 5";

        List<StatusRecord> statusList = new ArrayList<StatusRecord>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    StatusRecord status = new StatusRecord();
                    status.setId(cursor.getInt(0));
                    status.setCheckDate(cursor.getString(1));
                    status.setHeight(cursor.getInt(2));
                    status.setWeight(cursor.getFloat(3));
                    status.setBmi(cursor.getFloat(4));
                    status.setBody_status(cursor.getInt(5));
                    status.setDifference(cursor.getFloat(6));
                    status.setActivities(cursor.getString(7));

                    statusList.add(status);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return statusList;
        }catch (Exception e){
            Log.e(TAG, "getLast5 query execution failed!", e);
        }
        return null;
    }
}
