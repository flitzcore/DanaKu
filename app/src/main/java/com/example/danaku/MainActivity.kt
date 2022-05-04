package com.example.danaku

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.danaku.Database.DBHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var addButton:FloatingActionButton=findViewById(R.id.fab_add)
        addButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, AddActicity::class.java)
            startActivity(intent)
        })
        var simpleList:ListView=findViewById(R.id.lv_data)
        var list= mutableListOf<ModelList>()
        simpleList.adapter=CustomAdapter(this,R.layout.list_data,list)

        list.add(ModelList("sss","pemasukan",1020,"haro"))
        val db = DBHelper(this, null)

        // below is the variable for cursor
        // we have called method to get
        // all names from our database
        // and add to name text view
        val cursor = db.getName()

        // moving the cursor to first position and
        // appending value in the text view
        cursor!!.moveToFirst()
        /*
        list.add(ModelList(
            getString(cursor.getColumnIndex(DBHelper.NAMA_COL)),
            getString(cursor.getColumnIndex(DBHelper.JENIS_COL)),
            cursor.getColumnIndex(DBHelper.JUMLAH_COL),
            getString(cursor.getColumnIndex(DBHelper.DATE_COL))))


        // moving our cursor to next
        // position and appending values
        //

        while(cursor.moveToNext()){
            list.add(ModelList(
                getString(cursor.getColumnIndex(DBHelper.NAMA_COL)),
                getString(cursor.getColumnIndex(DBHelper.JENIS_COL)),
                cursor.getColumnIndex(DBHelper.JUMLAH_COL),
                getString(cursor.getColumnIndex(DBHelper.DATE_COL))))
        } */

        // at last we close our cursor
        cursor.close()

        simpleList.setOnItemClickListener { parent:AdapterView<*>, view: View, position:Int, id:Long ->

        }
    }
}