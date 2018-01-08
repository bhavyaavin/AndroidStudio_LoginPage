package com.example.asus.iotapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Asus on 2017-11-07.
 */

public class DatabaseHelp extends SQLiteOpenHelper {

    public static final String TAG = DatabaseHelp.class.getSimpleName();
    public static final String DATABASE_NAME = "IoT.db";
    public static final int DATABASE_VERSION = 1;

    public static final String USER_TBL ="Users";
    public static final String CRED_TBL ="Cred";
    public static final String COLMN_ID ="_id";
    public static final String COLMN_KEY_ID ="_keyid";
    public static final String COLMN_USRNAME = "username";
    public static final String COLMN_EMAIL = "email";
    public static final String COLMN_PASSWORD = "password";
    public static final String COLMN_PASS = "pass";

    /*
      Creating "Users" and Credential tables in the database
     */
    public static final String CREATE_TABLE_USERS = "Create Table" + USER_TBL +"("
            + COLMN_ID + "INTERGER PRIMARY KEY AUTOINCREMENT,"
            + COLMN_EMAIL + "TEXT,"
            + COLMN_PASSWORD + "TEXT);";
    public static final String CREATE_TABLE_CRED = "Create Table" + CRED_TBL +"("
            + COLMN_KEY_ID + "INTERGER PRIMARY KEY AUTOINCREMENT,"
            + COLMN_USRNAME + "TEXT,"
            + COLMN_PASS + "TEXT);";

    public DatabaseHelp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CRED);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TBL);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + CRED_TBL);
        onCreate(db);

    }
    /*
      Function to store user email id and password in to database.
     */
    public void StoreUserDetails(String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLMN_EMAIL, email);
        values.put(COLMN_PASSWORD, password);

        long id = db.insert(USER_TBL, null, values);
        db.close();

        Log.d(TAG, "User Stored" + id);
    }

    public boolean getUser(String Email, String Password)
    {
        String selectQuery = "select * from " + USER_TBL + "where" + COLMN_EMAIL + "="
                + "'" + Email + "'" + " and " + COLMN_PASSWORD + " = " + " ' " + Password + " ' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            return true;
        }
        cursor.close();
        db.close();

        return  false;
    }

    public void StoreCredentials(String username, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLMN_USRNAME, username);
        values.put(COLMN_PASS, pass);

        long id = db.insert(CRED_TBL, null, values);
        db.close();

        Log.d(TAG, "User Stored" + id);
    }

    public boolean getCred(String Username, String Pass)
    {
        String selectQuery = "select * from " + USER_TBL + "where" + COLMN_EMAIL + "="
                + "'" + Username + "'" + " and " + COLMN_PASSWORD + " = " + " ' " + Pass + " ' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            return true;
        }
        cursor.close();
        db.close();

        return  false;
    }
}
