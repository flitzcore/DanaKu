package com.example.danaku

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var simpleList:ListView=findViewById(R.id.lv_data)
        var list= mutableListOf<ModelList>()
        simpleList.adapter=CustomAdapter(this,R.layout.list_data,list)

        list.add(ModelList("Biaya berobat",true,50000,"kemarin"))
        list.add(ModelList("Pemasukan",false,50000,"kemarin"))
        list.add(ModelList("Allowance",false,50000,"kemarin"))
        list.add(ModelList("Biaya makan",true,50000,"kemarin"))
        list.add(ModelList("Biaya minum",true,50000,"kemarin"))
        list.add(ModelList("Biaya jalan",true,50000,"kemarin"))
        list.add(ModelList("Biaya makan",true,50000,"kemarin"))
        list.add(ModelList("Biaya makan",true,50000,"kemarin"))
        list.add(ModelList("Biaya makan",true,50000,"kemarin"))
        list.add(ModelList("Biaya makan",true,50000,"kemarin"))
        list.add(ModelList("Pemasukan",false,50000,"kemarin"))
        list.add(ModelList("Allowance",false,50000,"kemarin"))

        simpleList.setOnItemClickListener { parent:AdapterView<*>, view: View, position:Int, id:Long ->

        }
    }
}