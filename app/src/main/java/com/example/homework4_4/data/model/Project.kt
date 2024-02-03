package com.example.homework4_4.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Project(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("project_id")
    val id: Int = 0,
    @ColumnInfo("name")
    val name: String,
    var type: TypeOfProject
)
enum class TypeOfProject(
    val id: Int
) {
    ALL_PROJECT(0),
    MY_PROJECT(1),
    WORK(2),
    STUDIES(3)
}
