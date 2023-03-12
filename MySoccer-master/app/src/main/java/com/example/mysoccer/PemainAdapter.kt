package com.example.mysoccer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PemainAdapter (private val context: Context, private val pemain: List<Pemain>, val listener: (Pemain)-> Unit)
    : RecyclerView.Adapter<PemainAdapter.PemainViewHolder>(){
    class PemainViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imgPemain = view.findViewById<ImageView>(R.id.img_item_photo)
        val namePemain = view.findViewById<TextView>(R.id.tv_item_name)
        val descPemain = view.findViewById<TextView>(R.id.tv_item_description)

        fun bindView(pemain: Pemain, listener: (Pemain) -> Unit){
            imgPemain.setImageResource(pemain.imgPemain)
            namePemain.text = pemain.namePemain
            descPemain.text = pemain.descPemain
            itemView.setOnClickListener {
                listener(pemain)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PemainViewHolder {
        return PemainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_pemain, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PemainViewHolder, position: Int) {
        holder.bindView(pemain[position], listener)
    }

    override fun getItemCount(): Int = pemain.size
    }

