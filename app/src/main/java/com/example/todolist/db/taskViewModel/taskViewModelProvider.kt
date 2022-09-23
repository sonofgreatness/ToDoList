package com.example.todolist.db.taskViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class taskViewModelProviderFactory(
    val app: Application):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return taskViewModel(app) as T
    }
}