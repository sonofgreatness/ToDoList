package com.example.todolist.db.taskViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.db.taskModels.TASK
import com.example.todolist.db.TaskDatabase
import com.example.todolist.db.taskRepos.taskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class taskViewModel(application: Application) : AndroidViewModel(application) {
    //interacts with persistent data
    val listOfAllTasks: LiveData<List<TASK>>
    private val repository: taskRepository

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = taskRepository(taskDao)
        listOfAllTasks = repository.listOfAllTasks
    }

    fun addTask(task: TASK) {
viewModelScope.launch (Dispatchers.IO){
    repository.addTask(task)
}


    }
    fun deleteTask(task: TASK) {

        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(task)
        }

    }
    fun getListOfAllTasks()
    {
        repository.listOfAllTasks
    }
    fun updateDatabase(ID: Int, task: TASK) {}
    fun clearDB() {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.clearDB()
        }
    }


}