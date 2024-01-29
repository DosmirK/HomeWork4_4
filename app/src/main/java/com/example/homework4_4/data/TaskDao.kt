package com.example.homework4_4.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.homework4_4.model.Tasks
import com.example.homework4_4.model.TypeOfTask

@Dao
interface TaskDao {

    @Insert
    fun addTask(task: Tasks)

    @Delete
    fun remove(task:Tasks)

    @Query("SELECT * FROM tasks WHERE id =:id")
    fun getTaskById(id: Int): Tasks?

    @Query("SELECT * FROM tasks")
    fun getAll(): LiveData<List<Tasks>>

    @Query("SELECT * FROM tasks WHERE type = :taskType")
    fun getTasksByType(taskType: TypeOfTask): LiveData<List<Tasks>>
}