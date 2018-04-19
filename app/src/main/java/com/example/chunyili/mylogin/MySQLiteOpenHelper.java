package com.example.chunyili.mylogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chunyili on 2018/4/14.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "AccountDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Accounts";
    private static final String COL_account = "account";
    private static final String COL_pswd = "pswd";
    private static final String COL_id = "id";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_account + " TEXT, "+
                    COL_pswd + " TEXT ); ";

    public MySQLiteOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public List<Account> getAllAccount(int id){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COL_account, COL_pswd};
        Cursor cursor = db.query
                (TABLE_NAME,columns,null,null,null,null,null);
        List<Account> accountList = new ArrayList<>();
        while(cursor.moveToNext()){
            String theAccount = cursor.getString(0);
            String thePassword = cursor.getString(1);
            Account account = new Account(theAccount,thePassword);
            accountList.add(account);
        }
        cursor.close();
        return accountList;
    }

    public Account findById(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {COL_account, COL_pswd};
        String selection = COL_id + " = ?;";
        String [] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        Account account = null;
        if(cursor.moveToNext()){
            String theAccount = cursor.getString(0);
            String thePassword = cursor.getString(1);
            account = new Account(theAccount,thePassword);
        }
        cursor.close();
        return account;
    }

    public long insert(Account account){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(COL_account,account.getAccount());
        values.put(COL_pswd,account.getPswd());
        return db.insert(TABLE_NAME,null,values);
    }

    public int update(Account account){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_account,account.getAccount());
        values.put(COL_pswd,account.getPswd());
        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {Integer.toString(account.getId())};
        return db.update(TABLE_NAME,values,whereClause,whereArgs);
    }


    public boolean checkExist(Account account){
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {COL_account, COL_pswd};
        String sql =
                "SELECT * FROM Accounts WHERE account = ? AND pswd = ? ";
        String[] args = {account.getAccount(),account.getPswd()};
        Cursor cursor = db.rawQuery(sql,args);
        if(cursor.moveToNext()){
            return true;
        }else{
            return false;
        }
    }

}
