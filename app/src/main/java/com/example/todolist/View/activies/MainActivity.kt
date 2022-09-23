package com.example.todolist.View.activies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.View.fragments.home
import com.example.todolist.db.taskViewModel.taskViewModel
import com.example.todolist.db.taskViewModel.taskViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: taskViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelProviderFactory = taskViewModelProviderFactory(application)
        viewModel = ViewModelProvider(this,viewModelProviderFactory)[taskViewModel::class.java]
         replaceMainActivityWithHomeFrag()

    }
    private fun replaceMainActivityWithHomeFrag() {
        // 1.get the fragment manager
       val  fragmentManager : FragmentManager = getSupportFragmentManager();
       //2. use fragment manager to perform fragment transaction
        fragmentManager.beginTransaction()
                .replace(R.id.mainActivityParentView, home())
    }

}