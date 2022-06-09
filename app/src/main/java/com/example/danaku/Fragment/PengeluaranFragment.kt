package com.example.danaku.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.danaku.Database.DBHelper
import com.example.danaku.MainActivity
import com.example.danaku.R
import java.text.SimpleDateFormat
import java.util.*


class PengeluaranFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_pengeluaran, container, false)
        // Inflate the layout for this fragment
        val bt_pengeluaran= view?.findViewById<AppCompatButton>(R.id.button_tambahPengeluaran)

        val et_namaPengeluaran=view?.findViewById<EditText>(R.id.et_NamaPengeluaran)
        val et_nominalPengeluaran=view?.findViewById<EditText>(R.id.et_nominalPengeluaran)

        bt_pengeluaran?.setOnClickListener{
            val db = DBHelper(requireActivity(), null)

            // creating variables for values
            // in name and age edit texts
            val nama = et_namaPengeluaran?.text.toString()
            val nominal = et_nominalPengeluaran?.text.toString().toInt()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val currentDate = sdf.format(Date())
            // calling method to add
            // name to our database
            db.tambahData(nama, nominal,currentDate,"PENGELUARAN")


            // at last, clearing edit texts
            et_namaPengeluaran?.text?.clear()
            et_nominalPengeluaran?.text?.clear()
            activity?.let{
                Toast.makeText(it, "Pengeluaran Berhasil Ditambahkan", Toast.LENGTH_LONG).show()
                val intent = Intent (it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }
        return view
    }
    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}