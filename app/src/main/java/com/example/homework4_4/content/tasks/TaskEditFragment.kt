package com.example.homework4_4.content.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homework4_4.R
import com.example.homework4_4.data.TaskManager
import com.example.homework4_4.databinding.FragmentTaskEditBinding
import com.example.homework4_4.model.Tasks
import com.example.homework4_4.model.TypeOfTask

class TaskEditFragment : Fragment() {

    private var _binding: FragmentTaskEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        binding.btnSave.setOnClickListener {
           TaskManager.dao.addTask(
               Tasks(
                   text = binding.etTask.text.toString(),
                   type = when (binding.rbGroup.checkedRadioButtonId) {
                       binding.rbInUrgentTask.id -> TypeOfTask.URGENT_TASKS
                       binding.rbCompletedTask.id -> TypeOfTask.COMPLETED
                       else -> TypeOfTask.LONG_TASKS
                   }
               )
           )
            findNavController().navigateUp()
        }
    }
}