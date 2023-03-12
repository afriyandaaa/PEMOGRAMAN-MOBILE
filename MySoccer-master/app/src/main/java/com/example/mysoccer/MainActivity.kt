package com.example.mysoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //untuk bisa menghubungkan ke detailpemainactivity
    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pemainList = listOf<Pemain>(

            //data yang akan ditampilkan
            Pemain(
                R.drawable.mbape,
                "Kylian Mbappe",
                "Kylian Mbappé Lottin adalah seorang pemain sepak bola profesional " +
                        "Prancis yang bermain sebagai penyerang untuk Paris Saint-Germain dan " +
                        "tim nasional Prancis."
            ),
            Pemain(
                R.drawable.neymar,
                "Neymar",
                "Neymar da Silva Santos Júnior yang umumnya dikenal " +
                        "sebagai Neymar atau Neymar Jr, adalah pemain sepak bola " +
                        "profesional Brasil yang bermain untuk klub Prancis Paris Saint-Germain. " +
                        "Dia bermain sebagai penyerang atau pemain sayap."
            ),
            Pemain(
                R.drawable.messi,
                "Lionel Messi",
                "Lionel Andrés Messi juga dikenal sebagai Leo Messi, adalah seorang " +
                        "pemain sepak bola profesional asal Argentina yang bermain sebagai " +
                        "penyerang untuk klub Ligue 1 Paris Saint-Germain dan merupakan " +
                        "kapten tim nasional Argentina."
            ),
            Pemain(
                R.drawable.ramos,
                "Sergio Ramos",
                "Sergio Ramos merupakan seorang pemain sepak bola asal Spanyol " +
                        "yang bermain untuk klub Paris Saint-Germain pada posisi bek tengah. " +
                        "Ia dianggap sebagai bek yang paling terbaik di dunia."
            ),
            Pemain(
                R.drawable.zlatan,
                "Zlatan Ibrahimovic",
                "Zlatan Ibrahimovic adalah pemain sepak bola profesional Swedia " +
                        "yang bermain sebagai striker untuk klub AC Milan dan tim nasional Swedia."
            ),
            Pemain(
                R.drawable.mauro,
                "Mauro Icardi",
                "Mauro Emanuel Icardi Rivero adalah seorang pemain sepak bola " +
                        "berkewarganegaraan Argentina yang bermain untuk klub Galatasaray S.K. " +
                        "biasa bermain pada posisi penyerang. Icardi memulai karier juniornya" +
                        " di klub Vecindario dan Barcelona kemudian memulai karier seniornya " +
                        "di klub Sampdoria."
            ),
            Pemain(
                R.drawable.dimaria,
                "Angel DiMaria",
                "Ángel Fabián di María Hernández merupakan seorang pemain sepak bola " +
                        "Argentina yang kini bermain untuk klub Juventus dan Argentina. " +
                        "Dia dapat berposisi sebagai gelandang sayap atau winger."
            ),
            Pemain(
                R.drawable.hengki,
                "Hugo Ekitike",
                "Hugo Ekitike adalah pemain sepak bola profesional Prancis " +
                        "yang bermain sebagai striker untuk klub Ligue 1 Paris Saint-Germain, " +
                        "dengan status pinjaman dari Reims."
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_pemain)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = PemainAdapter(this,pemainList){
            val intent = Intent(this, DetailPemainActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)

        }
    }
}