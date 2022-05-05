package com.example.danaku.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.danaku.ModelList

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTO INCREMENT, " +
                NAMA_COL + " TEXT," +
                JENIS_COL + " TEXT," +
                DATE_COL + " TEXT," +
                JUMLAH_COL + " INTEGER" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun tambahData(nama : String, jumlah : Int , date : String, jenis: String){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAMA_COL, nama)
        values.put(JENIS_COL,jenis)
        values.put(DATE_COL,date)
        values.put(JUMLAH_COL, jumlah)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    @SuppressLint("Range")
    fun getAllData(): ArrayList<ModelList> {
        val dataList: ArrayList<ModelList> =ArrayList()
        val selectQuery="SELECT * FROM  $TABLE_NAME"
        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase
        val cursor: Cursor?
        try{
            cursor= db.rawQuery(selectQuery,null)

        }
        catch(e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return  ArrayList()
        }
        var nama:String
        var jenis:String
        var biaya: Int
        var tanggal: String
        if(cursor.moveToFirst()){
            do{
                nama=cursor.getString(cursor.getColumnIndex(NAMA_COL))
                jenis= cursor.getString(cursor.getColumnIndex(JENIS_COL))
                biaya=cursor.getInt(cursor.getColumnIndex(JUMLAH_COL))
                tanggal=cursor.getString(cursor.getColumnIndex(DATE_COL))

                val model=ModelList(nama,jenis,biaya,tanggal)
                dataList.add(model)
            }while (cursor.moveToNext())
        }
        return  dataList
    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "DANAKU"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "Biaya_table"

        val ID_COL="Id"

        val JENIS_COL ="jenis"

        val DATE_COL="date"

        // below is the variable for name column
        val NAMA_COL = "name"

        // below is the variable for age column
        val JUMLAH_COL = "jumlah"
    }
}
