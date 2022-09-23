package com.example.todolist.View.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.View.activies.MainActivity
import com.example.todolist.View.adapters.taskAdapter
import com.example.todolist.databinding.FragmentHome2Binding
import com.example.todolist.db.taskModels.TASK
import com.example.todolist.db.taskViewModel.taskViewModel

class home : Fragment() {
    //Viewbinding
    private lateinit var  _binding :FragmentHome2Binding
    val binding get() = _binding
    private lateinit var rootView: View
private lateinit var mViewModel :taskViewModel
private lateinit var adapter: taskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHome2Binding.inflate(inflater,container,false)
        rootView = binding.root
        //Use Activity viewmodel
        mViewModel = (activity as MainActivity).viewModel
        adapter = taskAdapter()
        setupRecyclerView()
        populateRecyclerView()
        setAddTaskButtonListener()
        setDeleteAllTasksButtonListener()
        return rootView
    }


    private fun createTaskUsingaString(taskstring: String) : TASK {
    return TASK(0,taskstring,false)
    }

    private fun getTextFromInputEditText():String {
        val task : String = rootView.findViewById<EditText>(R.id.input_task).text.toString()
        return task
    }

    private fun clearInputEditText() {
        rootView.findViewById<EditText>(R.id.input_task).setText("")
    }

    private fun addTaskTodatabase(textFromInputEditText: String) {
        val taskObject : TASK = createTaskUsingaString(textFromInputEditText)
        if(!textFromInputEditText.isEmpty())
        {
            mViewModel.addTask(taskObject)
        }
        else
        {
            Toast.makeText(requireActivity(), "fill in textbox", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setAddTaskButtonListener() {
        rootView.findViewById<Button>(R.id.add_task_button).setOnClickListener({
            addTaskTodatabase(getTextFromInputEditText())
            clearInputEditText()
        })
    }


    private fun populateRecyclerView() {
   mViewModel.listOfAllTasks.observe(viewLifecycleOwner, Observer {
            adapter.differ.submitList(it.toList())
            Log.d("dblist","$it")
            Log.d("adaptervalue","${adapter.itemCount}")
        })

    }
    private fun setupRecyclerView() {
        rootView.apply {
val recyclerView :RecyclerView =findViewById<RecyclerView>(R.id.list1)
recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }
    }
    private fun setDeleteAllTasksButtonListener() {
rootView.findViewById<Button>(R.id.delete_button).setOnClickListener({
    mViewModel.clearDB()
    Toast.makeText(requireActivity(), "database cleared", Toast.LENGTH_SHORT).show()
})
    }
}