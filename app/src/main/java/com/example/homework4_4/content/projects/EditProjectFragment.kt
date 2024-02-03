package com.example.homework4_4.content.projects

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homework4_4.data.db.DatabaseManager
import com.example.homework4_4.data.model.Project
import com.example.homework4_4.data.model.TypeOfProject
import com.example.homework4_4.databinding.FragmentEditProjectBinding
class EditProjectFragment : Fragment() {

    private var _binding: FragmentEditProjectBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            DatabaseManager.projectDao.addProject(
                Project(
                    name = binding.editText.text.toString(),
                    type = when (binding.rbGroup.checkedRadioButtonId) {
                        binding.rbInUrgentTask.id -> TypeOfProject.MY_PROJECT
                        binding.rbCompletedTask.id -> TypeOfProject.STUDIES
                        else -> TypeOfProject.WORK
                    }
                )
            )
            findNavController().navigateUp()
        }
    }
}