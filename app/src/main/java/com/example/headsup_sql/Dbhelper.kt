package com.example.headsup_sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract


class Dbhelper(cont: Context): SQLiteOpenHelper(cont, "heads up",null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        if(db != null){
            db.execSQL("create table celeb(id INTEGER PRIMARY KEY,Name text,taboo1 text,taboo2 text,taboo3 text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun addceleb(n:String, tb1:String, tb2:String, tb3:String): Long {
        val db = this.writableDatabase
        val cv= ContentValues()
        cv.put("Name",n)
        cv.put("taboo1",tb1)
        cv.put("taboo2",tb2)
        cv.put("taboo3",tb3)
        var st= db.insert("celeb",null,cv)
        db.close()
        return st
    }

    @SuppressLint("Range")
    fun getall():ArrayList<Celeb>{
        val db = this.readableDatabase
        var list=arrayListOf<Celeb>()
        val query="SELECT * from celeb"
        var cursor : Cursor? = null
        try {
            cursor=db.rawQuery(query,null)
        }catch (e:Exception){
            db.execSQL(query)
            return ArrayList()
        }
        var id=""
        var name=""
        var tb1=""
        var tb2=""
        var tb3=""
        if(cursor.count > 0) {
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getInt(cursor.getColumnIndex("id")).toString()
                    name = cursor.getString(cursor.getColumnIndex("Name"))
                    tb1 = cursor.getString(cursor.getColumnIndex("taboo1"))
                    tb2 = cursor.getString(cursor.getColumnIndex("taboo2"))
                    tb3 = cursor.getString(cursor.getColumnIndex("taboo3"))
                    list.add(Celeb(id, name, tb1, tb2, tb3))
                } while (cursor.moveToNext())
            }
        }
        db.close()
        return list
    }
    fun updateceleb(celeb:Celeb ): Int {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put("Name",celeb.name)
        cv.put("taboo1",celeb.taboo1)
        cv.put("taboo2",celeb.taboo2)
        cv.put("taboo3",celeb.taboo3)

        val success = db.update("celeb", cv, "id = ${celeb.id}", null)

        db.close()
        return success
    }

    fun deleteceleb(celeb:Celeb): Int{
        val db = this.writableDatabase
        val success = db.delete("celeb", "id = ${celeb.id}", null)
        db.close()
        return success
//        success > 0 means it worked
    }
}