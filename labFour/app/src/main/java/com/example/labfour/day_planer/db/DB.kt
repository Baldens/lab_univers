package com.example.labfour.day_planer.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class DB(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "planner.db"
        private const val TABLE_PLANNER = "list"
        private const val ID = "id"
        private const val NAME = "name"
        private const val DESCRIPTION = "description"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createDBPlanner = ("CREATE TABLE " + TABLE_PLANNER + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + DESCRIPTION + " TEXT" + ")")

        p0?.execSQL(createDBPlanner)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS  $TABLE_PLANNER")
        onCreate(p0)
    }

    fun insertPlanner(std: ModelPlanner): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID,std.id)
        contentValues.put(NAME,std.name)
        contentValues.put(DESCRIPTION,std.description)

        val success = db.insert(TABLE_PLANNER, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range", "Recycle")
    fun getAllPlanner(): ArrayList<ModelPlanner>{
        val stdList: ArrayList<ModelPlanner> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_PLANNER;"
        val db = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var description: String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                description = cursor.getString(cursor.getColumnIndex("description"))

                val std = ModelPlanner(id = id, name = name, description = description)
                stdList.add(std)
            }while (cursor.moveToNext())
        }

        return stdList
    }

    @SuppressLint("Range", "Recycle")
    fun getPlanner(id:Int): ArrayList<ModelPlanner>{
        val stdList: ArrayList<ModelPlanner> = ArrayList()
        val selectQuery = String.format("SELECT * FROM $TABLE_PLANNER WHERE $ID = %s;", id)
        val db = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var description: String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                description = cursor.getString(cursor.getColumnIndex("description"))

                val std = ModelPlanner(id = id, name = name, description = description)
                stdList.add(std)
            }while (cursor.moveToNext())
        }

        return stdList
    }

    @SuppressLint("Recycle")
    fun deletePlanner(id: Int){
        val selectQuery = "DELETE FROM $TABLE_PLANNER WHERE $ID = $id;"
        val db = this.readableDatabase
        try{
            db.execSQL(String.format("DELETE FROM $TABLE_PLANNER WHERE $ID = %s;", id));
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
        }
    }
}