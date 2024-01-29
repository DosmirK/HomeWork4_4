package com.example.homework4_4.ui.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val img: String,
    val name: String
): Parcelable
