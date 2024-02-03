package com.example.homework4_4.data.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework4_4.databinding.ItemListBinding

class TasksAdapter(
    private val taskUpdated: (Task) -> Unit
): ListAdapter<Task, TasksAdapter.TaskViewHolder>(
    object : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    }

) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            binding = ItemListBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class TaskViewHolder(
        private val binding: ItemListBinding,
    ): ViewHolder(binding.root){

        fun onBind(task: Task){
            binding.tvTask.text = task.name
            binding.cbDone.isChecked = task.done
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                taskUpdated(task.copy(done = isChecked))
            }
        }
    }
}