package com.example.danaku

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager.widget.ViewPager
import com.example.danaku.Adapter.Adapter_AddActivity
import com.example.danaku.Database.DBHelper
import com.example.danaku.Fragment.PemasukanFragment
import com.example.danaku.Fragment.PengeluaranFragment
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*

class AddActicity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_acticity)




        setUpTabs()
    }
    private fun setUpTabs(){
        val adapter= Adapter_AddActivity(supportFragmentManager)
        val viewPager=findViewById<ViewPager>(R.id.viewPager)
        val tabs= findViewById<TabLayout>(R.id.tabs)
        adapter.addFragment(PemasukanFragment(),"Pemasukan")
        adapter.addFragment(PengeluaranFragment(),"Pengeluaran")


        viewPager.adapter=adapter
        tabs.setupWithViewPager(viewPager)


    }
}