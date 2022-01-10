package com.example.crud_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TambahObatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_obat)

        val txtNama = findViewById<EditText>(R.id.txtNama)
        val txtJenisObat = findViewById<EditText>(R.id.txtJenisObat)
        val txtHargaObat = findViewById<EditText>(R.id.txtHarga)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        btnSimpan.setOnClickListener {
            val dbSaya = MyDBHelper(this)
            dbSaya.tambahObat(
                txtNama.text.toString().trim(),
                txtJenisObat.text.toString().trim(),
                Integer.valueOf(txtHargaObat.text.toString().trim())
            )
        }

    }
}