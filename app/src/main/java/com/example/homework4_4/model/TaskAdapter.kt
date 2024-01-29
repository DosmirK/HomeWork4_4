package com.example.homework4_4.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework4_4.databinding.ItemAllTaskBinding
import com.example.homework4_4.databinding.ItemCompletedTasksBinding
import com.example.homework4_4.databinding.ItemLongTaskBinding
import com.example.homework4_4.databinding.ItemUrgentTaskBinding

class TaskAdapter(
    private val onClick: (Tasks) -> Unit
): ListAdapter<Tasks, RecyclerView.ViewHolder>(

    object : DiffUtil.ItemCallback<Tasks>() {
        override fun areItemsTheSame(oldItem: Tasks, newItem: Tasks) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Tasks, newItem: Tasks) = oldItem == newItem
    }

) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TypeOfTask.URGENT_TASKS.id -> TaskUrgentViewHolder(
                ItemUrgentTaskBinding.inflate(inflater, parent, false)
            )
            TypeOfTask.LONG_TASKS.id -> TaskLongViewHolder(
                ItemLongTaskBinding.inflate(inflater, parent, false)
            )
            TypeOfTask.ALL_TASKS.id -> AllTasksViewHolder(
                ItemAllTaskBinding.inflate(inflater, parent, false)
            )
            else -> CompletedTasksViewHolder(
                ItemCompletedTasksBinding.inflate(inflater, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TaskUrgentViewHolder -> holder.onBind(getItem(position))
            is TaskLongViewHolder -> holder.onBind(getItem(position))
            is AllTasksViewHolder -> holder.onBind(getItem(position))
            is CompletedTasksViewHolder -> holder.onBind(getItem(position))
        }
        holder.itemView.setOnClickListener {
            onClick(getItem(position))
        }
    }
}

class TaskUrgentViewHolder(private val binding: ItemUrgentTaskBinding) : ViewHolder(binding.root){

    fun onBind(task: Tasks) {
        binding.tvTaskUrgent.text = task.text
    }
}

class TaskLongViewHolder(private val binding: ItemLongTaskBinding): ViewHolder(binding.root){

    fun onBind(task: Tasks) {
        binding.tvTaskLong.text = task.text
    }
}

class AllTasksViewHolder(private val binding: ItemAllTaskBinding): ViewHolder(binding.root){

    fun onBind(task: Tasks) {
        binding.tvAllTasks.text = task.text
    }
}

class CompletedTasksViewHolder(private val binding: ItemCompletedTasksBinding): ViewHolder(binding.root){

    fun onBind(task: Tasks) {
        binding.tvCompletedTasks.text = task.text
    }
}