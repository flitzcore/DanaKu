package com.example.danaku

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.danaku.Database.DBHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var dataList: ArrayList<ModelList>
    var nominalPemasukan=0
    var nominalPengeluaran=0
    var list = mutableListOf<ModelList>()



    override fun onCreate(savedInstanceState: Bundle?) {
        //Log.d("TES782", "tesss")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var addButton: FloatingActionButton = findViewById(R.id.fab_add)
        addButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, AddActicity::class.java)
            startActivity(intent)
        })



        // list.add(ModelList("sss","pemasukan",1020,"haro"))
        val db = DBHelper(this, null)


        var jenis_data="Pengeluaran"//setJenisData()


        dataList = db.getAllData()
        val tv_total=findViewById<TextView>(R.id.tvTotalPengeluaran)
        val tv_nominal=findViewById<TextView>(R.id.tvPengeluaran)
        tv_total.text="Total Pengeluaran"
        tv_nominal.text="Rp "+"%,.0f".format(Locale.GERMAN, nominalPengeluaran.toDouble())
        for(i in dataList){
            if(i.jenis=="PEMASUKAN")nominalPemasukan+=i.biaya
            if(i.jenis=="PENGELUARAN")nominalPengeluaran+=i.biaya
        }
        setJangkaWaktu()
        setJenisData()

        val tv_sisaSaldo=findViewById<TextView>(R.id.tvSaldo)
        tv_sisaSaldo.text="Rp "+"%,.0f".format(Locale.GERMAN, (nominalPemasukan-nominalPengeluaran).toDouble())

       // simpleList.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->

        //}
    }
    fun setAdapter(){
        var simpleList: ListView = findViewById(R.id.lv_data)

        val ListAdapter= CustomAdapter(this, R.layout.list_data, list)
        simpleList.adapter= ListAdapter
    }

    fun updateData(jenis_data: String){
        println(jenis_data)

        val tv_total=findViewById<TextView>(R.id.tvTotalPengeluaran)
        val tv_nominal=findViewById<TextView>(R.id.tvPengeluaran)

        for(i in dataList){
            if(i.jenis=="PENGELUARAN" && jenis_data=="Pengeluaran") {
                //val tv_total=findViewById<TextView>(R.id.tvTotalPengeluaran)
                //val tv_nominal=findViewById<TextView>(R.id.tvPengeluaran)
                println("jenis_pengeluaran")

                tv_total.text="Total Pengeluaran"
                tv_nominal.text="Rp "+"%,.0f".format(Locale.GERMAN, nominalPengeluaran.toDouble())
                list.add(i)
            }
            else if(i.jenis=="PEMASUKAN" && jenis_data=="Pemasukan") {
                //val tv_total=findViewById<TextView>(R.id.tvTotalPengeluaran)
                //val tv_nominal=findViewById<TextView>(R.id.tvPengeluaran)
                println("jenis_pemasukan")
                tv_total.text="Total Pemasukan"
                tv_nominal.text="Rp "+"%,.0f".format(Locale.GERMAN, nominalPemasukan.toDouble())
                list.add(i)
            }
        }
    setAdapter()

    }
    private fun setJenisData(){
        val jenisData = resources.getStringArray(R.array.jenisData)
        val spinner = findViewById<Spinner>(R.id.jenisData)
        var jenisDataReturn="Pengeluaran"
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, jenisData
            )
            adapter.setDropDownViewResource(R.layout.spinner_style)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        "Menampilkan "+jenisData[position], Toast.LENGTH_SHORT
                    ).show()
                    jenisDataReturn=jenisData[position]
                    val tv_total=findViewById<TextView>(R.id.tvTotalPengeluaran)
                    tv_total.text="Total ${jenisDataReturn}"
                    list.clear()
                    updateData(jenisDataReturn)
                    println("exe")
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    jenisDataReturn=jenisData[0]
                    updateData(jenisDataReturn)
                }

            }

        }
    }
    private fun setJangkaWaktu():String{
        val jangkaWaktu = resources.getStringArray(R.array.jangka_waktu)
        val spinner = findViewById<Spinner>(R.id.jangkaWaktu)
        var jangkaWaktuReturn="Tahun ini"
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, jangkaWaktu
            )
            adapter.setDropDownViewResource(R.layout.spinner_style)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        jangkaWaktu[position], Toast.LENGTH_SHORT
                    ).show()
                    jangkaWaktuReturn=jangkaWaktu[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                    jangkaWaktuReturn=jangkaWaktu[0]
                }
            }
        }
        //this.recreate()
        return jangkaWaktuReturn
    }
}