package com.example.todolist.db.taskRepos
import androidx.lifecycle.LiveData
import com.example.todolist.db.taskModels.TASK
import com.example.todolist.db.taskDaos.taskDao


class taskRepository(private val taskDao: taskDao) {

    val listOfAllTasks : LiveData<List<TASK>>  = taskDao.getEveryone()

suspend fun addTask(task: TASK)
{
taskDao.addTask(task)
}
    suspend fun deleteTask(task: TASK)
    {
        val Id: Int = task.columnid
        taskDao.deleteTask(Id)
    }

suspend fun clearDB()
{
 taskDao.deleteAllTasks()
}


}