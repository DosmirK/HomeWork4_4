package com.example.homework4_4.utils

import android.app.Application
import com.example.homework4_4.data.db.DatabaseManager

class App: Application() {

    var mySharedPreferences: MySharedPreferences? = null

    override fun onCreate() {
        super.onCreate()

        DatabaseManager.init(this)
        mySharedPreferences = MySharedPreferences(this)
    }

    companion object{
        var userName: String = ""
    }
}