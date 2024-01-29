package com.example.homework4_4.data

import android.content.Context
import androidx.room.Room

object TaskManager {
    private var _dao: TaskDao? = null
    val dao get() = _dao!!

    fun init(context: Context){
        val database = Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "tasks"
        ).allowMainThreadQueries().build()

        _dao = database.taskDao()
    }
}