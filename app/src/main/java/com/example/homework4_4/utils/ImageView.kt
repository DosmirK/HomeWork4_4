package com.example.homework4_4.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.homework4_4.R

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).placeholder(R.drawable.baseline_account_circle_24).circleCrop().into(this)
}