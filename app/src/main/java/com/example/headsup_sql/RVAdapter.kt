package com.example.headsup_sql

import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val rv: ArrayList<celeb>, val cont: Context): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {
    lateinit var ctit:TextView
    lateinit var tb1:TextView
    lateinit var tb2:TextView
    lateinit var tb3:TextView
    lateinit var rvlist:CardView
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.itemView.apply {
            uiin(holder)
            ctit.text = rv[position].name
            tb1.text= rv[position].taboo1
            tb2.text=rv[position].taboo2
            tb3.text=rv[position].taboo3



        }
    }
    fun uiin(holder: ItemViewHolder){
        holder.itemView.apply {
            rvlist= findViewById<CardView>(R.id.rvlisting)
            ctit= findViewById<TextView>(R.id.cardtitle)
            tb1 = findViewById<TextView>(R.id.tb1)
            tb2 = findViewById<TextView>(R.id.tb2)
            tb3 = findViewById<TextView>(R.id.tb3)
        }
    }

    override fun getItemCount() = rv.size
}