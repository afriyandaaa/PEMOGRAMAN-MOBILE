package com.example.mysoccer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailPemainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pemain)

        //perintah menampilkan battom back
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //inialisasi data dari pemain
        val pemain = intent.getParcelableExtra<Pemain>(MainActivity.INTENT_PARCELABLE)

        // untuk mengambil data pemain yang ada di layout detail pemain
        val imgPemain = findViewById<ImageView>(R.id.img_item_photo)
        val namePemain = findViewById<TextView>(R.id.tv_item_name)
        val descPemain = findViewById<TextView>(R.id.tv_item_description)
        imgPemain.setImageResource(pemain?.imgPemain!!)
        namePemain.text = pemain.namePemain
        descPemain.text = pemain.descPemain

    }
    //dijalankan nya battom back
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}