package com.example.rey.model
//program dibawah ini akan menghapus juka terjadi eror
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Rey(
   @StringRes val stringResourceId: Int,
   @DrawableRes val imageResourceId: Int
    )
