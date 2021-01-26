package com.example.itemtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //for item table
    public static final String ITEM_TABLE_NAME = "ITEM_TABLE";
    public static final String ITEM_TABLE_ID = "ID";
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String ITEM_ID = "ITEM_ID";
    public static final String ITEM_MODEL = "ITEM_MODEL";
    public static final String ITEM_DESC = "ITEM_DESC";


    // for report missing table
    public static final String REPORTED_ITEM_TABLE = "REPORTED_ITEM_TABLE";
    public static final String REPORTED_ITEM_NAME = "ITEM_NAME";
    public static final String REPORTED_MISSING_STATUS = "MISSING";
    public static final String REPORTED_BROKEN_STATUS = "BROKEN";
    public static final String REPORTED_ITEM_DETAILS = "ITEM_DETAILS";
    public static final String REPORTED_ITEM_ID = "ITEM_ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "test.db", null, 1);
    }

    // This is the first time a database is accessed. Should be a code to create database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + REPORTED_ITEM_TABLE + " (" + REPORTED_ITEM_NAME + " TEXT , " + REPORTED_ITEM_ID + " TEXT PRIMARY KEY , " + REPORTED_MISSING_STATUS + " BOOL, " + REPORTED_BROKEN_STATUS + " BOOL, " + REPORTED_ITEM_DETAILS + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //    public String getDetails(ReportItemModel reportItemModel){
//        String returnDetails = new String();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT " + COLUMN_ITEM_DETAILS + " FROM " + REPORTED_ITEM_TABLE;
//        return returnDetails;
//
//    }

    // report missing table - add row
    public boolean addOne(ReportItemModel reportItemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(REPORTED_ITEM_NAME, reportItemModel.getName());
        cv.put(REPORTED_ITEM_ID, reportItemModel.getId());
        cv.put(REPORTED_MISSING_STATUS, reportItemModel.isMissing());
        cv.put(REPORTED_BROKEN_STATUS, reportItemModel.isBroken());
        cv.put(REPORTED_ITEM_DETAILS, reportItemModel.getDetails());

        long insert = db.insert(REPORTED_ITEM_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    // item table - add row
    public boolean addOneItem(ItemModel itemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ITEM_NAME, itemModel.getItemName());
        cv.put(ITEM_ID, itemModel.getItemId());
        cv.put(ITEM_MODEL, itemModel.getItemModel());
        cv.put(ITEM_DESC, itemModel.getItemDesc());

        long insert = db.insert(ITEM_TABLE_NAME, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    // report missing table - delete row
    public boolean deleteOne(ReportItemModel reportItemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + REPORTED_ITEM_TABLE + " WHERE " +  REPORTED_ITEM_ID + " = " + reportItemModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public List<ReportItemModel> getAll(){
        List<ReportItemModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + REPORTED_ITEM_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            do{
                String itemName = cursor.getString(0);
                String itemId = cursor.getString(1);
                boolean isMissing = cursor.getInt(2) == 1 ? true: false;
                boolean isBroken = cursor.getInt(3) == 1 ? true: false;
                String itemDetails = cursor.getString(4);

                ReportItemModel newItem = new ReportItemModel(itemName, itemId, isMissing, isBroken, itemDetails);
                returnList.add(newItem);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;

    }
}
