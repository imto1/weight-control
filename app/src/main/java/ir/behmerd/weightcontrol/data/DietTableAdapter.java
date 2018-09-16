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
 * Database DIET table adapter class.
 */
public class DietTableAdapter extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "DIET";
    private static final String TAG = "DIET_TABLE_ADAPTER";

    // Constructor
    public DietTableAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Commands
    public boolean insert(DietRecord diet){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", diet.getTitle());
        contentValues.put("WEEK_DAY", diet.getWeekDay());
        contentValues.put("BREAKFAST", diet.getBreakfast());
        contentValues.put("MORNING_SNACK", diet.getMorningSnack());
        contentValues.put("LUNCH", diet.getLunch());
        contentValues.put("EVENING_MEAL", diet.getEveningMeal());
        contentValues.put("DINNER", diet.getDinner());
        contentValues.put("BEFORE_BEDTIME", diet.getBeforeBedtime());
        try {
            db.insert(TABLE_NAME, null, contentValues);
            db.close();
            return true;
        }catch (Exception e){
            Log.e(TAG, "Insert operation failed!", e);
        }
        return false;
    }

    public boolean update(DietRecord diet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", diet.getTitle());
        contentValues.put("WEEK_DAY", diet.getWeekDay());
        contentValues.put("BREAKFAST", diet.getBreakfast());
        contentValues.put("MORNING_SNACK", diet.getMorningSnack());
        contentValues.put("LUNCH", diet.getLunch());
        contentValues.put("EVENING_MEAL", diet.getEveningMeal());
        contentValues.put("DINNER", diet.getDinner());
        contentValues.put("BEFORE_BEDTIME", diet.getBeforeBedtime());
        try {
            db.update(TABLE_NAME, contentValues, "ID = ? ",
                    new String[]{Integer.toString(diet.getId())});
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
    public DietRecord getRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME +
                " WHERE ID = " + String.valueOf(id);

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            DietRecord diet = new DietRecord();
            if (cursor != null && cursor.moveToFirst()){
                diet.setId(cursor.getInt(0));
                diet.setTitle(cursor.getString(1));
                diet.setWeekDay(cursor.getInt(2));
                diet.setBreakfast(cursor.getString(3));
                diet.setMorningSnack(cursor.getString(4));
                diet.setLunch(cursor.getString(5));
                diet.setEveningMeal(cursor.getString(6));
                diet.setDinner(cursor.getString(7));
                diet.setBeforeBedtime(cursor.getString(8));
                cursor.close();
            }
            return diet;
        }catch (Exception e){
            Log.e(TAG, "getRecord query execution failed!", e);
        }
        return null;
    }

    public List<DietRecord> getAllRecords(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        List<DietRecord> dietList = new ArrayList<DietRecord>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    DietRecord diet = new DietRecord();
                    diet.setId(cursor.getInt(0));
                    diet.setTitle(cursor.getString(1));
                    diet.setWeekDay(cursor.getInt(2));
                    diet.setBreakfast(cursor.getString(3));
                    diet.setMorningSnack(cursor.getString(4));
                    diet.setLunch(cursor.getString(5));
                    diet.setEveningMeal(cursor.getString(6));
                    diet.setDinner(cursor.getString(7));
                    diet.setBeforeBedtime(cursor.getString(8));

                    dietList.add(diet);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return dietList;
        }catch (Exception e){
            Log.e(TAG, "getAllRecords query execution failed!", e);
        }
        return null;
    }
}
