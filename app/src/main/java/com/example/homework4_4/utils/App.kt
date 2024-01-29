package com.example.homework4_4.utils

import android.app.Application
import com.example.homework4_4.data.TaskManager
import com.example.homework4_4.utils.MySharedPreferences

class App: Application() {

    var mySharedPreferences: MySharedPreferences? = null

    override fun onCreate() {
        super.onCreate()

        TaskManager.init(this)
        mySharedPreferences = MySharedPreferences(this)
    }

    companion object{
        var userName: String = ""
    }
}