package com.example.todolist.View.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.db.taskModels.TASK

class taskAdapter: RecyclerView.Adapter<taskAdapter.MyViewHolder>() {

    //Use DiffUtil to update Adapter if list values change , it is more effecient than notifyDatasetChanged
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private val differCallback = object : DiffUtil.ItemCallback<TASK>() {
        override fun areItemsTheSame(oldItem: TASK, newItem: TASK): Boolean {
            return oldItem.task == newItem.task
        }

        override fun areContentsTheSame(oldItem: TASK, newItem: TASK): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.checkbox_template,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = differ.currentList[position]

        holder.apply {
            this.itemView.findViewById<CheckedTextView>(R.id.check).setText (task.task)
            this.itemView.findViewById<CheckBox>(R.id.checkbox).isChecked= task.isChecked
        }


    }

    override fun getItemCount(): Int {
return differ.currentList.size
    }
}