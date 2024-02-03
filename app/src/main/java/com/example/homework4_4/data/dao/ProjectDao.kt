package com.example.homework4_4.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.homework4_4.data.model.Project
import com.example.homework4_4.data.model.ProjectAndTasks

@Dao
interface ProjectDao {
    @Insert
    fun addProject(project: Project)
    @Delete
    fun remove(project: Project)
    @Query("SELECT * FROM project")
    fun getProjects(): LiveData<List<Project>>
    @Transaction
    @Query("SELECT * FROM project")
    fun getProjectWithTasks(): LiveData<List<ProjectAndTasks>>
}