package com.example.homework4_4.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.homework4_4.data.model.Project
import com.example.homework4_4.data.model.Task
import com.example.homework4_4.data.model.ProjectAndTasks

@Dao
interface TaskDao {

    @Insert
    fun addTask(task: Task)

    @Delete
    fun remove(task: Task)

    @Update
    fun update(task: Task)
    @Transaction
    @Query("SELECT * FROM task WHERE project_id =:projectId")
    fun getTasksBayProjectId(projectId: Int): List<Task>

}