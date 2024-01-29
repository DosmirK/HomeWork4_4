package com.example.homework4_4.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var text: String,
    var type: TypeOfTask,
)

enum class TypeOfTask(
    val id: Int
) {
    ALL_TASKS(0),
    URGENT_TASKS(1),
    LONG_TASKS(2),
    COMPLETED(3)
}
