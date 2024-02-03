package com.example.homework4_4.data.db

import android.content.Context
import androidx.room.Room
import com.example.homework4_4.data.dao.ProjectDao
import com.example.homework4_4.data.dao.TaskDao
import com.example.homework4_4.data.db.MyDatabase

object DatabaseManager {
    lateinit var taskDao: TaskDao
    lateinit var projectDao: ProjectDao

    fun init(context: Context) {
        val db = Room.databaseBuilder(
            context = context,
            klass = MyDatabase::class.java,
            name = "my_database"
        ).allowMainThreadQueries().build()

        taskDao = db.taskDao()
        projectDao = db.projectDao()
    }

}