package com.example.headsup_sql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var edn: EditText
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var add:Button
    lateinit var dbh: Dbhelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        add.setOnClickListener{
            if(edn.text.isNotEmpty()&&edt1.text.isNotEmpty()&&edt2.text.isNotEmpty()&&edt3.text.isNotEmpty()){
                var st=dbh.addnote(edn.text.toString(),edt1.text.toString(),edt2.text.toString(),edt3.text.toString())
                Toast.makeText(this,"note saved in row$st", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun init() {
        edn = findViewById(R.id.edcelep)
        edt1 = findViewById(R.id.edtb1)
        edt2 = findViewById(R.id.edtb2)
        edt3 = findViewById(R.id.edtb3)
        add = findViewById(R.id.add)
        dbh =Dbhelper(this)
    }
}