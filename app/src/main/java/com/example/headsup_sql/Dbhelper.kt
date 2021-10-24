package com.example.headsup_sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class Dbhelper(cont: Context): SQLiteOpenHelper(cont, "heads up",null, 1) {

    private val sqldb:SQLiteDatabase =writableDatabase
    private val sqldbr:SQLiteDatabase =readableDatabase

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

    @SuppressLint("Range")
    fun getall():ArrayList<celeb>{
        var list=arrayListOf<celeb>()
        val query="SELECT * from celeb"
        var cursor : Cursor? = null
        try {
            cursor=sqldbr.rawQuery(query,null)
        }catch (e:Exception){
            sqldbr.execSQL(query)
            return ArrayList()
        }
        var name=""
        var tb1=""
        var tb2=""
        var tb3=""
        if(cursor.moveToFirst()){
            do {
                name=cursor.getString(cursor.getColumnIndex("Name"))
                tb1=cursor.getString(cursor.getColumnIndex("taboo1"))
                tb2=cursor.getString(cursor.getColumnIndex("taboo2"))
                tb3=cursor.getString(cursor.getColumnIndex("taboo3"))
                list.add(celeb(name,tb1,tb2,tb3))
            }while(cursor.moveToNext())
        }
        return list
    }
}