package com.example.homework4_4.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Project::class,
            parentColumns = ["project_id"],
            childColumns = ["project_id"]
        )
    ]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("primary_key")
    val id: Int = 0,
    @ColumnInfo("project_id")
    val projectId: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("done")
    val done: Boolean
)
