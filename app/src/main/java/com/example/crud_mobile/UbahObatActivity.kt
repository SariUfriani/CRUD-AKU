package com.example.crud_mobile

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class UbahObatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_obat)

        val actionBar =supportActionBar
        if (intent.hasExtra("nama")) {
            actionBar?.title = intent.getStringExtra("nama")
        }
        val btnUbah = findViewById<Button>(R.id.btn_ubah)

        getIntentData()

        btnUbah.setOnClickListener {
            val dbKita = MyDBHelper(this)

            val idObat = intent.getStringExtra("id")
            val namaObat = findViewById<EditText>(R.id.txt_nama).text.toString()
            val jenisObat = findViewById<EditText>(R.id.txt_jenisobat).text.toString()
            val hargaObat = findViewById<EditText>(R.id.txt_harga).text.toString()

            dbKita.ubahObat(idObat, namaObat, jenisObat, hargaObat)
        }
        val  btnHapus = findViewById<Button>(R.id.btnHapus)
        btnHapus.setOnClickListener {
            dialogKonfirmasi()
        }
    }
    fun getIntentData() {
        if (
            intent.hasExtra("id") &&
            intent.hasExtra("nama") &&
            intent.hasExtra("jenisobat") &&
            intent.hasExtra("harga")
        ){

            val idobat          = intent.getStringExtra("id")
            val namaobat       = intent.getStringExtra("nama")
            val jenisobat   = intent.getStringExtra("jenisobat")
            val harga      = intent.getStringExtra("harga")

            val txtnama = findViewById<EditText>(R.id.txt_nama)
            val txtjenisobat = findViewById<EditText>(R.id.txt_jenisobat)
            val txtharga = findViewById<EditText>(R.id.txt_harga)

            txtnama.setText(namaobat)
            txtjenisobat.setText(jenisobat)
            txtharga.setText(harga)
        } else{
            Toast.makeText(this,"Tidak Ada Data !", Toast.LENGTH_SHORT).show()
        }
    }
    fun dialogKonfirmasi(){
        val idObat  = intent.getStringExtra("id")
        val namaObat = intent.getStringExtra("nama")

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete " + namaObat + " ?")
        alertDialog.setMessage("Apakah anda yakin menghapus " + namaObat + " ?")

        alertDialog.setPositiveButton("iya", DialogInterface.OnClickListener{dialog, which ->
            val dbKita = MyDBHelper(this)
            dbKita.hapusObat(idObat)
            startActivity(Intent(this, MainActivity::class.java))
        })
        alertDialog.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
        alertDialog.create().show()
    }

}