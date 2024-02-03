package com.example.homework4_4.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProjectAndTasks (
    @Embedded val project: Project,
    @Relation(
        parentColumn = "project_id",
        entityColumn = "primary_key"
    )
    val tasks: List<Task>
    )
