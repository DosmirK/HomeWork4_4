package com.example.homework4_4.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework4_4.data.dao.ProjectDao
import com.example.homework4_4.data.dao.TaskDao
import com.example.homework4_4.data.model.Project
import com.example.homework4_4.data.model.Task

@Database(
    entities = [
        Task::class,
        Project::class
    ],
    version = 1
)
abstract class MyDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun projectDao(): ProjectDao
}