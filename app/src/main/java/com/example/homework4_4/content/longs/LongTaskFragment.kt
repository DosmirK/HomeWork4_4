package com.example.homework4_4.content.longs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homework4_4.R
import com.example.homework4_4.data.TaskManager
import com.example.homework4_4.databinding.FragmentLongTaskBinding
import com.example.homework4_4.model.TaskAdapter
import com.example.homework4_4.model.Tasks
import com.example.homework4_4.model.TypeOfTask

class LongTaskFragment : Fragment() {

    private var _binding: FragmentLongTaskBinding? = null
    private val binding get() = _binding!!
    val taskAdapter = TaskAdapter(onClick = this::onClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLongTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTasks.adapter = taskAdapter
        initListener()
    }

    private fun initListener() {
        TaskManager.dao.getTasksByType(TypeOfTask.LONG_TASKS).observe(viewLifecycleOwner) { tasks ->
            taskAdapter.submitList(tasks)
        }
    }

    private fun onClick(item: Tasks){
        findNavController().navigate(R.id.taskEditFragment)
    }
}