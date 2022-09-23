package com.example.todolist.db.taskModels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="TaskTable")
data class TASK
    (
    @PrimaryKey (autoGenerate = true)
    val columnid:Int,
    val task:String,
    val isChecked:Boolean
    )