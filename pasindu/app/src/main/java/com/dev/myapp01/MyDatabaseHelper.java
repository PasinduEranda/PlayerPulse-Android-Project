package com.dev.myapp01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PlayerPulse.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "players";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_INNINGS = "innings";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_WICKETS = "wickets";
    private static final String COLUMN_IMAGE = "image";


    MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_INNINGS + " INTEGER, " +
                        COLUMN_SCORE + " INTEGER, " +
                        COLUMN_WICKETS + " INTEGER) ;";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public long addPlayer(String name, int innings, int score, int wickets) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("innings", innings);
        values.put("score", score);
        values.put("wickets", wickets);

        // Insert the player details into the database
        long id = db.insert(TABLE_NAME, null, values);
        db.close();

        return id; // Return the ID of the newly inserted row
    }

    Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updatePlayer(String row_id, String name, String innings, String score, String wickets, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_INNINGS, innings);
        cv.put(COLUMN_SCORE, score);
        cv.put(COLUMN_WICKETS, wickets);
        //cv.put(COLUMN_IMAGE, image);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }

    }
    void deletePlayer(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }

    }
}
