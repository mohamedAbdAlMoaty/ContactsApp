package com.example.nh.contactsapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nh.contactsapp.models.Data;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String Database_Name = "DataBase";
    private static final int Database_Version = 3;

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, Database_Version);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table mytable(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ,phone TEXT,email TEXT)");
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS mytable"); // Drop older table if existed
        onCreate(db);                               // Create tables again
    }





    // insert row
    public void insertdata(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("insert into mytable(name,phone,email) VALUES('" + data.getName() + "','" + data.getPhone() + "','" + data.getEmail() + "')");
        db.close();
    }







    // Getting All rows
    public ArrayList<Data> getAllrows() {
        ArrayList <Data> List= new ArrayList<Data>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from mytable", null);
        cursor.moveToFirst();

     while (cursor.moveToNext()) {                              // او الى فوق
            int id= cursor.getInt(0);
            //int id=cursor.getInt(cursor.getColumnIndex("id"));                   // هى هى
            String namee= cursor.getString(1);
            // String namee=cursor.getString(cursor.getColumnIndex("name"));                // هى هى
            String phonee= cursor.getString(2);
            // String phonee=cursor.getString(cursor.getColumnIndex("phone"));               //  هى هى
         String emaill= cursor.getString(3);
         // String emaill=cursor.getString(cursor.getColumnIndex("email"));               //  هى هى
         Data data=new Data(id,namee,phonee,emaill);
            List.add(data);                         // Adding contact to list

        }
        return List;
    }





    // update data
    public void updatedata(int id,Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update mytable set name = '" + data.getName()+ "', phone = '" + data.getPhone() + "', email = '" + data.getEmail()+ "' where id =" + id);
        db.close();
    }





     // Deleting single row
    public void deleterow(int Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM  mytable where id=" + Id);
        db.close();
    }



}