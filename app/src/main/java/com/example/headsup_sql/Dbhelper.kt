package com.example.headsup_sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class Dbhelper(cont: Context): SQLiteOpenHelper(cont, "heads up",null, 1) {

    private val sqldb:SQLiteDatabase =writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if(db != null){
            db.execSQL("create table celeb(Name text,taboo1 text,taboo2 text,taboo3 text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun addnote(n:String,tb1:String,tb2:String,tb3:String): Long {
        val cv= ContentValues()
        cv.put("Name",n)
        cv.put("taboo1",tb1)
        cv.put("taboo2",tb2)
        cv.put("taboo3",tb3)
        var st= sqldb.insert("celeb",null,cv)
        return st
    }
}