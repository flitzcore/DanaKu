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

class PemasukanFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_pemasukan, container, false)
        val bt_pemasukan= view?.findViewById<AppCompatButton>(R.id.button_tambahPemasukan)
        val et_namaPemasukan=view?.findViewById<EditText>(R.id.et_NamaPemasukan)
        val et_nominalPemasukan=view?.findViewById<EditText>(R.id.et_nominalPemasukan)
        bt_pemasukan?.setOnClickListener{
            print("tes")
            val db = DBHelper(requireActivity(), null)

            // creating variables for values
            // in name and age edit texts
            val nama = et_namaPemasukan?.text.toString()
            val nominal = et_nominalPemasukan?.text.toString().toInt()
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            // calling method to add
            // name to our database
            db.tambahData(nama, nominal,currentDate,"PEMASUKAN")

            // Toast to message on the screen


            // at last, clearing edit texts
            et_namaPemasukan?.text?.clear()
            et_nominalPemasukan?.text?.clear()
            activity?.let{
                Toast.makeText(it, "Pemasukan Berhasil Ditambahkan", Toast.LENGTH_LONG).show()
                val intent = Intent (it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }


        return view
    }
}