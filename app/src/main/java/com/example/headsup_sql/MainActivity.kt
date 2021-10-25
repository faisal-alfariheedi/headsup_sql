package com.example.headsup_sql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var edn: EditText
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var rv: RecyclerView
    lateinit var add:Button
    lateinit var dbh: Dbhelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        add.setOnClickListener{
            if(edn.text.isNotEmpty()&&edt1.text.isNotEmpty()&&edt2.text.isNotEmpty()&&edt3.text.isNotEmpty()){
                var st=dbh.addceleb(edn.text.toString(),edt1.text.toString(),edt2.text.toString(),edt3.text.toString())
                Toast.makeText(this,"note saved in row$st", Toast.LENGTH_SHORT).show()
                setuprvdata()
            }
        }
    }
    fun init() {
        edn = findViewById(R.id.edcelep)
        edt1 = findViewById(R.id.edtb1)
        edt2 = findViewById(R.id.edtb2)
        edt3 = findViewById(R.id.edtb3)
        rv = findViewById(R.id.rvm)
        add = findViewById(R.id.add)
        dbh =Dbhelper(this)
        setuprvdata()
    }
    fun setuprvdata() {
        var list=dbh.getall()
        rv.adapter=RVAdapter(list,this)
        rv.layoutManager= LinearLayoutManager(this)
    }
    /////////////////////menu////////////////////////////


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        val item1 = menu!!.getItem(0)
        item1.setTitle("switch to game")


        return super.onPrepareOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.m1 -> {
                intent= Intent(this,Game::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
