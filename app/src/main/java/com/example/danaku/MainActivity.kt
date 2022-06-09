package com.example.danaku

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.danaku.Database.DBHelper
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var dataList: ArrayList<ModelList>
    var nominalPemasukan=0
    var nominalPengeluaran=0
    var sisaSaldo=0
    var list = mutableListOf<ModelList>()
    var jenis_data="Pengeluaran"
    var jangka_waktu="Tahun ini"



    override fun onCreate(savedInstanceState: Bundle?) {
        //Log.d("TES782", "tesss")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var addButton: FloatingActionButton = findViewById(R.id.fab_add)
        addButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, AddActicity::class.java)
            startActivity(intent)
        })

        val db = DBHelper(this, null)


        dataList = db.getAllData()
        UpdateSisaSaldo()


        setJangkaWaktu()
        setJenisData()




       // simpleList.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->

        //}
    }
    fun setChart(){
        val calendar = Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val entries: MutableList<BarEntry> = ArrayList()

        for(i in dataList){
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val date = formatter.parse(i.tanggal)
            val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
            cal.time = date
            val localYear=cal.get(Calendar.YEAR)
            val localMonth=cal.get(Calendar.MONTH)

            if(jangka_waktu=="Tahun ini"){

            }
        }
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 80f))
        entries.add(BarEntry(2f, 60f))
        entries.add(BarEntry(3f, 50f))
        // gap of 2f
        // gap of 2f
        entries.add(BarEntry(5f, 70f))
        entries.add(BarEntry(6f, 60f))
        val set = BarDataSet(entries, "BarDataSet")
    }
    fun setAdapter(){
        var simpleList: ListView = findViewById(R.id.lv_data)

        val ListAdapter= CustomAdapter(this, R.layout.list_data, list)
        simpleList.adapter= ListAdapter
    }
    fun UpdateSisaSaldo(){
        sisaSaldo=0
        for(i in dataList){
            if(i.jenis=="PENGELUARAN")sisaSaldo-=i.biaya
            else if(i.jenis=="PEMASUKAN")sisaSaldo+=i.biaya

        }
        val tv_sisaSaldo=findViewById<TextView>(R.id.tvSaldo)
        tv_sisaSaldo.text="Rp "+"%,.0f".format(Locale.GERMAN, (sisaSaldo).toDouble())
    }

    fun updateData(){
        println(jenis_data)
        println(jangka_waktu)
        if(jenis_data=="Pemasukan")nominalPemasukan=0
        if(jenis_data=="Pengeluaran")nominalPengeluaran=0


        val tv_nominal=findViewById<TextView>(R.id.tvPengeluaran)
        val calendar = Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)

        for(i in dataList){
            if(i.jenis=="PENGELUARAN" && jenis_data=="Pengeluaran") {
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val date = formatter.parse(i.tanggal)
                val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
                cal.time = date
                val localYear=cal.get(Calendar.YEAR)
                val localMonth=cal.get(Calendar.MONTH)


                if(jangka_waktu=="Tahun ini"&& localYear==year){
                    nominalPengeluaran+=i.biaya
                    println("exe")
                    list.add(i)
                }
                if(jangka_waktu=="Bulan ini"&& localMonth==month){
                    nominalPengeluaran+=i.biaya
                    list.add(i)
                }
                tv_nominal.text="Rp "+"%,.0f".format(Locale.GERMAN, nominalPengeluaran.toDouble())

            }
            else if(i.jenis=="PEMASUKAN" && jenis_data=="Pemasukan") {

                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val date = formatter.parse(i.tanggal)
                val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
                cal.time = date
                val localYear=cal.get(Calendar.YEAR)
                val localMonth=cal.get(Calendar.MONTH)


                if(jangka_waktu=="Tahun ini"&& localYear==year){
                    nominalPemasukan+=i.biaya
                    println("exe")
                    list.add(i)
                }
                if(jangka_waktu=="Bulan ini"&& localMonth==month){
                    nominalPemasukan+=i.biaya
                    list.add(i)
                }
                tv_nominal.text="Rp "+"%,.0f".format(Locale.GERMAN, nominalPemasukan.toDouble())
            }
        }
    setAdapter()
        UpdateSisaSaldo()


    }
    private fun setJenisData(){
        val jenisData = resources.getStringArray(R.array.jenisData)
        val spinner = findViewById<Spinner>(R.id.jenisData)
        var jenisDataReturn=jenis_data
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
                    jenis_data=jenisDataReturn
                    val tv_total=findViewById<TextView>(R.id.tvTotalPengeluaran)
                    tv_total.text="Total ${jenisDataReturn}"
                    list.clear()
                    updateData()

                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    jenisDataReturn=jenisData[0]
                    jenis_data=jenisDataReturn
                    updateData()
                }

            }

        }
    }
    private fun setJangkaWaktu():String{
        val jangkaWaktu = resources.getStringArray(R.array.jangka_waktu)
        val spinner = findViewById<Spinner>(R.id.jangkaWaktu)
        var jangkaWaktuReturn=jangka_waktu
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
                    jangka_waktu=jangkaWaktuReturn
                    list.clear()
                    updateData()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                    jangkaWaktuReturn=jangkaWaktu[0]
                    jangka_waktu=jangkaWaktuReturn
                    list.clear()
                    updateData()
                }
            }
        }
        //this.recreate()
        return jangkaWaktuReturn
    }
}