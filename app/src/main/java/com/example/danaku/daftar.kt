package com.example.danaku

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class daftar : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        auth= FirebaseAuth.getInstance()

        val bt_daftar=findViewById<Button>(R.id.button_daftar)
        val namaPengguna=findViewById<EditText>(R.id.et_NamaPenggunaDaftar)
        val password=findViewById<EditText>(R.id.et_kataSandi)
        val konfPassword=findViewById<EditText>(R.id.et_konfirmasiKataSandi)
        bt_daftar.setOnClickListener {

            val nama= namaPengguna.text.toString()
            val pass=password.text.toString()
            val konfPass=konfPassword.text.toString()
            if(pass==konfPass){register(nama,pass)}
            else{
                Toast.makeText(applicationContext,"Password dan konfirmasi belum sesuai",Toast.LENGTH_LONG).show()
            }

        }
        val tv_masuk=findViewById<TextView>(R.id.tv_ayoMasuk)
        tv_masuk.setOnClickListener {
            goToLogin(it)
        }

    }
    fun register( nama: String, pass:String){

        auth.createUserWithEmailAndPassword(nama,pass).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
    fun goToLogin(view: View){
        val intent= Intent(this,login::class.java)
        startActivity(intent)
    }

}