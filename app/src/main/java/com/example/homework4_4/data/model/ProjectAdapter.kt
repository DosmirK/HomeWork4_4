package com.example.homework4_4.data.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework4_4.databinding.ItemProjectBinding

class ProjectAdapter(
    private val itemUpdated: (Task) -> Unit
): ListAdapter<ProjectAndTasks, ProjectAdapter.ProjectViewHolder>(
    object : DiffUtil.ItemCallback<ProjectAndTasks>(){
        override fun areItemsTheSame(oldItem: ProjectAndTasks, newItem: ProjectAndTasks)
        = oldItem.project.id == newItem.project.id
        override fun areContentsTheSame(oldItem: ProjectAndTasks, newItem: ProjectAndTasks)
        = oldItem.tasks == newItem.tasks

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ItemProjectBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        binding.rvProjectTask.adapter = TasksAdapter(itemUpdated)
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ProjectViewHolder(
        private val binding: ItemProjectBinding
    ): ViewHolder(binding.root){
        fun onBind(project: ProjectAndTasks){
            binding.run {
                tvProject.text = project.project.name
                val adapter = rvProjectTask.adapter as TasksAdapter
                adapter.submitList(project.tasks)
            }
        }
    }
}