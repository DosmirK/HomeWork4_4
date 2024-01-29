package com.example.homework4_4.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework4_4.model.Tasks

@Database(
    entities = [Tasks::class],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}