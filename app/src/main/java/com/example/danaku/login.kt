package com.example.danaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()
        val button_masuk=findViewById<Button>(R.id.button_masuk)
        val et_namaPengguna=findViewById<EditText>(R.id.et_NamaPengguna)
        val et_kataSandi=findViewById<EditText>(R.id.et_katasandi)

        button_masuk.setOnClickListener {

            val nama=et_namaPengguna.text.toString()
            val sandi=et_kataSandi.text.toString()
            login(nama,sandi)
        }
        val tv_daftar=findViewById<TextView>(R.id.tv_ayoDaftar)
        tv_daftar.setOnClickListener {
            goToRegister(it)
        }
    }
    fun login(email:String, password:String){


        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext,"Selamat Datang", Toast.LENGTH_LONG).show()
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun goToRegister(view: View){
        val intent= Intent(this,daftar::class.java)
        startActivity(intent)
    }
}