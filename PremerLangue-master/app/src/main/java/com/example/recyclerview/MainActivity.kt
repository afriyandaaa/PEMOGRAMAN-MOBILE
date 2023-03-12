package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.recyclerview.adapter.AdapterTeamBola
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.Pemain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listPemain = ArrayList<Pemain>()
        listPemain.add(Pemain( nama = "barca" ,R.drawable.barca, posisi = "Penjaga Gawang", tinggi = "2.00m", tempatlahir = "belanda", tgllahir = "11 mei 1999"))
        listPemain.add(Pemain( nama = "madrid" , R.drawable.madrid, posisi = "Tiang bendera", tinggi = "1,80m", tempatlahir = "indonesia", tgllahir = "12 mei 1999"))
        listPemain.add(Pemain( nama = "chelsi" ,R.drawable.chelsi, posisi = "Pengambil bola", tinggi = "1,60m", tempatlahir = "belanda", tgllahir = "1 mei 1999"))

        binding.list.adapter = AdapterTeamBola(this,listPemain,object : AdapterTeamBola.OnClickListener {
            override fun detailData(item: Pemain?) {
                TODO("Not yet implemented")
            }

        })
    }

}