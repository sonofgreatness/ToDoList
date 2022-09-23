package com.example.todolist.db.taskDaos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.todolist.db.taskModels.TASK

@Dao
interface  taskDao {




    @Insert(onConflict = REPLACE)
    suspend fun addTask(task: TASK)

    @Query("SELECT * FROM TaskTable")
     fun getEveryone():LiveData<List<TASK>>

    @Query("DELETE FROM TaskTable WHERE columnid = :columnId")
    suspend fun deleteTask (columnId: Int) 

    @Query ("UPDATE Tasktable SET isChecked = 0 WHERE columnid = :columnId")
    suspend fun updateCheckedTofalse(columnId: Int)

    @Query("UPDATE Tasktable SET isChecked = 1 WHERE columnid = :columnId")
    suspend fun updateCheckedToTrue(columnId: Int)

    @Query("DELETE  FROM TaskTable")
    suspend fun deleteAllTasks()




}