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
 * Database PROGRAM table adapter class.
 */
public class ProgramTableAdapter extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "PROGRAM";
    private static final String TAG = "PROGRAM_TABLE_ADAPTER";

    // Constructor
    public ProgramTableAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Commands
    public boolean insert(ProgramRecord program){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", program.getTitle());
        contentValues.put("START_DATE", program.getStartDate());
        contentValues.put("END_DATE", program.getEndDate());
        contentValues.put("DIET_ID", program.getDietId());
        contentValues.put("DONE", program.getDone());
        try {
            db.insert(TABLE_NAME, null, contentValues);
            db.close();
            return true;
        }catch (Exception e){
            Log.e(TAG, "Insert operation failed!", e);
        }
        return false;
    }

    public boolean update(ProgramRecord program) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", program.getTitle());
        contentValues.put("START_DATE", program.getStartDate());
        contentValues.put("END_DATE", program.getEndDate());
        contentValues.put("DIET_ID", program.getDietId());
        contentValues.put("DONE", program.getDone());
        try {
            db.update(TABLE_NAME, contentValues, "ID = ? ",
                    new String[]{Integer.toString(program.getId())});
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
    public ProgramRecord getRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME +
                " WHERE ID = " + String.valueOf(id);

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            ProgramRecord program = new ProgramRecord();
            if (cursor != null && cursor.moveToFirst()){
                program.setId(cursor.getInt(0));
                program.setTitle(cursor.getString(1));
                program.setStartDate(cursor.getString(2));
                program.setEndDate(cursor.getString(3));
                program.setDietId(cursor.getInt(4));
                program.setDone(Boolean.valueOf(cursor.getString(5)));
                cursor.close();
            }
            return program;
        }catch (Exception e){
            Log.e(TAG, "getRecord query execution failed!", e);
        }
        return null;
    }

    public List<ProgramRecord> getAllRecords(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        List<ProgramRecord> programList = new ArrayList<ProgramRecord>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    ProgramRecord program = new ProgramRecord();
                    program.setId(cursor.getInt(0));
                    program.setTitle(cursor.getString(1));
                    program.setStartDate(cursor.getString(2));
                    program.setEndDate(cursor.getString(3));
                    program.setDietId(cursor.getInt(4));
                    program.setDone(Boolean.valueOf(cursor.getString(5)));

                    programList.add(program);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return programList;
        }catch (Exception e){
            Log.e(TAG, "getAllRecords query execution failed!", e);
        }
        return null;
    }

    public ProgramRecord getProgram(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT PROGRAM.ID, PROGRAM.TITLE, PROGRAM.START_DATE," +
                " PROGRAM.END_DATE, DIET.TITLE, PROGRAM.DONE" +
                " FROM DIET INNER JOIN PROGRAM ON DIET.ID = PROGRAM.DIET_ID" +
                " WHERE PROGRAM.ID = " + String.valueOf(id) + ";";

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            ProgramRecord program = new ProgramRecord();
            if (cursor != null && cursor.moveToFirst()){
                program.setId(cursor.getInt(0));
                program.setTitle(cursor.getString(1));
                program.setStartDate(cursor.getString(2));
                program.setEndDate(cursor.getString(3));
                program.setDietTitle(cursor.getString(4));
                program.setDone(Boolean.valueOf(cursor.getString(5)));
                cursor.close();
            }
            return program;
        }catch (Exception e){
            Log.e(TAG, "getProgram query execution failed!", e);
        }
        return null;
    }

    public List<ProgramRecord> getAllPrograms(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT PROGRAM.ID, PROGRAM.TITLE, PROGRAM.START_DATE," +
                " PROGRAM.END_DATE, DIET.TITLE, PROGRAM.DONE" +
                " FROM DIET INNER JOIN PROGRAM ON DIET.ID = PROGRAM.DIET_ID;";

        List<ProgramRecord> programList = new ArrayList<ProgramRecord>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    ProgramRecord program = new ProgramRecord();
                    program.setId(cursor.getInt(0));
                    program.setTitle(cursor.getString(1));
                    program.setStartDate(cursor.getString(2));
                    program.setEndDate(cursor.getString(3));
                    program.setDietTitle(cursor.getString(4));
                    program.setDone(Boolean.valueOf(cursor.getString(5)));

                    programList.add(program);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return programList;
        }catch (Exception e){
            Log.e(TAG, "getAllPrograms query execution failed!", e);
        }
        return null;
    }
}
