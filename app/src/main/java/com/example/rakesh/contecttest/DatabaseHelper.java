package com.example.rakesh.contecttest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

/**
 * Created by rakesh on 25/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public  static  final String DATABASE_NAME="Student.db";
    public  static  final String TABLE_NAME="student_table";
    public  static  final String col_1="ID";
    public  static  final String col_2="NAME";
    public  static  final String col_3="EMAIL";
    public  static  final String col_4="MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,EMAIL TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    //insert data
    public  boolean insertData(String Name,String Email,String Marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,Name);
        contentValues.put(col_3,Email);
        contentValues.put(col_4,Marks);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return  true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;


    }
    //update data
    public  boolean updateData(String id ,String Name,String Email,String Marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, id);
        contentValues.put(col_2, Name);
        contentValues.put(col_3, Email);
        contentValues.put(col_4, Marks);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return  true;
    }
    //delete data
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] {id});

    }
}
