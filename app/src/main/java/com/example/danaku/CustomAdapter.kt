package com.example.danaku

import android.content.Context
import android.view.LayoutInflater


import android.view.View

import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class CustomAdapter (var myContext: Context, var resources: Int, var items:List<ModelList>):
    ArrayAdapter<ModelList>(myContext,resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater= LayoutInflater.from(myContext)
        val view: View=layoutInflater.inflate(resources,null)
        val tv_namadata:TextView=view.findViewById(R.id.tvNamaData)
        val tv_totalData:TextView=view.findViewById(R.id.tvTotalData)
        val list_img=view.findViewById<ImageView>(R.id.list_img)
        val myItem:ModelList=items[position]
        if(myItem.jenis=="PEMASUKAN")list_img.setImageResource(R.drawable.ic_baseline_arrow_circle_up_24)
        else list_img.setImageResource((R.drawable.ic_baseline_arrow_circle_down_24))
        tv_namadata.text=myItem.title
        tv_totalData.text= myItem.biaya.toString()
        return view
    }
}