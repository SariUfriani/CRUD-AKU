package com.example.crud_mobile

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val obat_id: ArrayList<String>      = arrayListOf()
    val obat_nama: ArrayList<String>   = arrayListOf()
    val obat_jenisobat: ArrayList<String> = arrayListOf()
    val obat_harga: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTambah = findViewById<FloatingActionButton>(R.id.float_add)
        btnTambah.setOnClickListener {
            val intentKita = Intent(this, TambahObatActivity::class.java)
            startActivity(intentKita)
        }
        simpanDataDiArray()
        val obatAdapter = ObatAdapter(this,obat_id,obat_nama,obat_jenisobat,obat_harga)
        val rv_obat = findViewById<RecyclerView>(R.id.rv_obat)
        rv_obat.adapter = obatAdapter
        rv_obat.layoutManager = LinearLayoutManager(this)
        rv_obat.itemAnimator = DefaultItemAnimator()
    }
    fun simpanDataDiArray(){
        val dbSaya            = MyDBHelper(this)
        val dataSaya: Cursor = dbSaya.BacaSemuaData()

        if(dataSaya.count == 0){
            Toast.makeText(this,"Data Tidak Ada!", Toast.LENGTH_SHORT).show()
        }
        else{
            while (dataSaya.moveToNext()){
                obat_id.add(dataSaya.getString(0))
                obat_nama.add(dataSaya.getString(1))
                obat_jenisobat.add(dataSaya.getString(2))
                obat_harga.add(dataSaya.getString(3))
            }
        }
    }
}