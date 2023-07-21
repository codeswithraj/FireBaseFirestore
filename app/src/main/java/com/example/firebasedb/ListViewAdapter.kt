package com.example.firebasedb

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.firebase.firestore.QuerySnapshot

class ListViewAdpater(val context: Context, var arrayList: List<DataModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return  arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return  arrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return  p0.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var rowView=LayoutInflater.from(context).inflate(R.layout.layout,p2,false)
        var data=arrayList[p0]
        rowView.findViewById<TextView>(R.id.first).text=data.name
        rowView.findViewById<TextView>(R.id.last).text=data.age
        rowView.findViewById<TextView>(R.id.born).text= data.city

        return  rowView
    }
}