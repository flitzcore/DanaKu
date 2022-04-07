package com.example.danaku

import android.content.Context
import android.view.LayoutInflater


import android.view.View

import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class CustomAdapter (var myContext: Context, var resources: Int, var items:List<ModelList>):
    ArrayAdapter<ModelList>(myContext,resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater= LayoutInflater.from(myContext)
        val view: View=layoutInflater.inflate(resources,null)
        val tv_namadata:TextView=view.findViewById(R.id.tvNamaData)
        val tv_totalData:TextView=view.findViewById(R.id.tvTotalData)
        val myItem:ModelList=items[position]
        tv_namadata.text=myItem.title
        tv_totalData.text= myItem.biaya.toString()
        return view
    }
}