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
 * Database REMIND table adapter class.
 */
public class RemindTableAdapter extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "REMIND";
    private static final String TAG = "REMIND_TABLE_ADAPTER";

    // Constructor
    public RemindTableAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Commands
    public boolean insert(RemindRecord remind){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("DIET_ID", remind.getDietId());
        contentValues.put("BREAKFAST_TIME", remind.getBreakfastTime());
        contentValues.put("SNACK_TIME", remind.getSnackTime());
        contentValues.put("LUNCH_TIME", remind.getLunchTime());
        contentValues.put("EVENING_TIME", remind.getEveningTime());
        contentValues.put("DINNER_TIME", remind.getDinnerTime());
        contentValues.put("BED_TIME", remind.getBedTime());
        try {
            db.insert(TABLE_NAME, null, contentValues);
            db.close();
            return true;
        }catch (Exception e){
            Log.e(TAG, "Insert operation failed!", e);
        }
        return false;
    }

    public boolean update(RemindRecord remind) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("DIET_ID", remind.getDietId());
        contentValues.put("BREAKFAST_TIME", remind.getBreakfastTime());
        contentValues.put("SNACK_TIME", remind.getSnackTime());
        contentValues.put("LUNCH_TIME", remind.getLunchTime());
        contentValues.put("EVENING_TIME", remind.getEveningTime());
        contentValues.put("DINNER_TIME", remind.getDinnerTime());
        contentValues.put("BED_TIME", remind.getBedTime());
        try {
            db.update(TABLE_NAME, contentValues, "ID = ? ",
                    new String[]{Integer.toString(remind.getId())});
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
    public RemindRecord getRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME +
                " WHERE ID = " + String.valueOf(id);

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            RemindRecord remind = new RemindRecord();
            if (cursor != null && cursor.moveToFirst()){
                remind.setId(cursor.getInt(0));
                remind.setDietId(cursor.getInt(1));
                remind.setBreakfastTime(cursor.getInt(2));
                remind.setSnackTime(cursor.getInt(3));
                remind.setLunchTime(cursor.getInt(4));
                remind.setEveningTime(cursor.getInt(5));
                remind.setDinnerTime(cursor.getInt(6));
                remind.setBedTime(cursor.getInt(7));
                cursor.close();
            }
            return remind;
        }catch (Exception e){
            Log.e(TAG, "getRecord query execution failed!", e);
        }
        return null;
    }

    public List<RemindRecord> getAllRecords(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        List<RemindRecord> remindList = new ArrayList<RemindRecord>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    RemindRecord remind = new RemindRecord();
                    remind.setId(cursor.getInt(0));
                    remind.setDietId(cursor.getInt(1));
                    remind.setBreakfastTime(cursor.getInt(2));
                    remind.setSnackTime(cursor.getInt(3));
                    remind.setLunchTime(cursor.getInt(4));
                    remind.setEveningTime(cursor.getInt(5));
                    remind.setDinnerTime(cursor.getInt(6));
                    remind.setBedTime(cursor.getInt(7));

                    remindList.add(remind);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return remindList;
        }catch (Exception e){
            Log.e(TAG, "getAllRecords query execution failed!", e);
        }
        return null;
    }

    public RemindRecord getRemind(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT REMIND.ID, DIET.TITLE, REMIND.BREAKFAST_TIME," +
                " REMIND.SNACK_TIME, REMIND.LUNCH_TIME, REMIND.EVENING_TIME," +
                " REMIND.DINNER_TIME, REMIND.BED_TIME" +
                " FROM DIET INNER JOIN REMIND ON DIET.ID = REMIND.DIET_ID" +
                " WHERE ID = " + String.valueOf(id) + ";";

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            RemindRecord remind = new RemindRecord();
            if (cursor != null && cursor.moveToFirst()){
                remind.setId(cursor.getInt(0));
                remind.setDietTitle(cursor.getString(1));
                remind.setBreakfastTime(cursor.getInt(2));
                remind.setSnackTime(cursor.getInt(3));
                remind.setLunchTime(cursor.getInt(4));
                remind.setEveningTime(cursor.getInt(5));
                remind.setDinnerTime(cursor.getInt(6));
                remind.setBedTime(cursor.getInt(7));
                cursor.close();
            }
            return remind;
        }catch (Exception e){
            Log.e(TAG, "getRemind query execution failed!", e);
        }
        return null;
    }

    public List<RemindRecord> getAllReminds(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT REMIND.ID, DIET.TITLE, REMIND.BREAKFAST_TIME," +
                " REMIND.SNACK_TIME, REMIND.LUNCH_TIME, REMIND.EVENING_TIME," +
                " REMIND.DINNER_TIME, REMIND.BED_TIME" +
                " FROM DIET INNER JOIN REMIND ON DIET.ID = REMIND.DIET_ID;";

        List<RemindRecord> remindList = new ArrayList<RemindRecord>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    RemindRecord remind = new RemindRecord();
                    remind.setId(cursor.getInt(0));
                    remind.setDietTitle(cursor.getString(1));
                    remind.setBreakfastTime(cursor.getInt(2));
                    remind.setSnackTime(cursor.getInt(3));
                    remind.setLunchTime(cursor.getInt(4));
                    remind.setEveningTime(cursor.getInt(5));
                    remind.setDinnerTime(cursor.getInt(6));
                    remind.setBedTime(cursor.getInt(7));

                    remindList.add(remind);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return remindList;
        }catch (Exception e){
            Log.e(TAG, "getAllReminds query execution failed!", e);
        }
        return null;
    }
}
