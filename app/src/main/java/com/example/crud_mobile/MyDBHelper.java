package com.example.crud_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "listObat.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME        = "OBAT";
    private static final String COLUMN_ID         = "_id";
    private static final String COLUMN_NAMA      = "obat_nama";
    private static final String COLUMN_JENISOBAT  = "obat_jenisobat";
    private static final String COLUMN_HARGA    = "obat_harga";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAMA + " TEXT, " +
                COLUMN_JENISOBAT + " TEXT, " +
                COLUMN_HARGA + " INTEGER" +
                ") ;";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void tambahObat(String nama, String jenisobat, int harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAMA, nama);
        cv.put(COLUMN_JENISOBAT, jenisobat);
        cv.put(COLUMN_HARGA, harga);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Gagal !", Toast.LENGTH_SHORT).show();
        } else  {
            Toast.makeText(context, "Data Ditambahkan !", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor BacaSemuaData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor dataSaya = null;
        if(db != null){
            dataSaya = db.rawQuery(query, null);
        }
        return dataSaya;
    }
    void ubahObat(String baris_id, String nama, String jenisobat, String harga){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataKita = new ContentValues();
        dataKita.put(COLUMN_NAMA, nama);
        dataKita.put(COLUMN_JENISOBAT, jenisobat);
        dataKita.put(COLUMN_HARGA, harga);

        long hasil = db.update(TABLE_NAME, dataKita, "_id=?", new String[]{baris_id});

        if (hasil == -1){
            Toast.makeText(context, "Ada Gangguan Berhasil di Ubah !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil di Ubah !", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusObat(String row_id) {
        SQLiteDatabase dbKita = this.getWritableDatabase();
        long result = dbKita.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "Gagal Delete !", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Berhasil Delete !", Toast.LENGTH_SHORT).show();
        }
    }
    }

