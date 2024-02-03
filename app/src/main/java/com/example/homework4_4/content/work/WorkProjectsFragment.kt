package com.example.homework4_4.content.work

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework4_4.data.db.DatabaseManager
import com.example.homework4_4.data.model.ProjectAdapter
import com.example.homework4_4.data.model.Task
import com.example.homework4_4.databinding.FragmentWorkProjectsBinding

class WorkProjectsFragment : Fragment() {

    private var _binding: FragmentWorkProjectsBinding? = null
    private val binding get() = _binding!!
    val projectAdapter = ProjectAdapter(itemUpdated = this::itemUpdated)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        binding.rvProjects.adapter = projectAdapter
    }

    private fun initListener() {
        DatabaseManager.projectDao.getProjectWithTasks().observe(viewLifecycleOwner) { data ->
            projectAdapter.submitList(data)
        }
    }
    private fun itemUpdated(task: Task){
        DatabaseManager.taskDao.update(task)
    }
}