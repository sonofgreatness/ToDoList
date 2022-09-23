package com.example.todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.db.taskDaos.taskDao
import com.example.todolist.db.taskModels.TASK
import com.example.todolist.db.taskModels.TASK_FTS

@Database(entities = [TASK::class, TASK_FTS::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {

//return a dao function with no parameters

abstract fun taskDao(): taskDao
// define how to initiate the datababse  here i will specify
// when to export db to a file ,when to instatiate a databse with a file


//first we beginwith normal instantiating :


      companion object {

          @Volatile//writes made to this field are made immediately made available to  all threads
          private var INSTANCE: TaskDatabase? = null

          fun getDatabase(context: Context): TaskDatabase {
              val tempInstance = INSTANCE
              if (tempInstance != null) {
                  return tempInstance
              }
              synchronized(this) {
                  val instance = Room.databaseBuilder(
                      context.applicationContext,
                      TaskDatabase::class.java,
                      "taskdata"
                  ).build()
                  INSTANCE = instance
                  return instance
              }

          }
      }



}