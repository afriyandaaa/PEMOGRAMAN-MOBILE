package com.example.mysoccer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
//model yang menampung variabel atau string data pemain bola
@Parcelize
data class Pemain(
    val imgPemain: Int,
    val namePemain: String,
    val descPemain: String
) : Parcelable

