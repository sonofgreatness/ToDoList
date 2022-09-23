package com.example.todolist.db.taskModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.example.todolist.db.taskModels.TASK

@Fts4(contentEntity = TASK::class)
@Entity(tableName = "Tasktable_fts")
class TASK_FTS
    (   @PrimaryKey
        @ColumnInfo(name = "rowid")
        val rowId: Int,
        val task:String,
        val isChecked:Boolean
            )
