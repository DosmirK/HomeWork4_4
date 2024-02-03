package com.example.homework4_4.content.projects

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.homework4_4.R
import com.example.homework4_4.data.db.DatabaseManager
import com.example.homework4_4.data.model.Project
import com.example.homework4_4.databinding.FragmentTaskEditBinding
import com.example.homework4_4.data.model.Task
import com.example.homework4_4.data.model.TypeOfProject
import com.example.homework4_4.utils.UIUtils.showKeyboard

class TaskEditFragment : Fragment() {

    private var _binding: FragmentTaskEditBinding? = null
    private val binding get() = _binding!!
    private val projects = mutableListOf<Project>()
    private var selectedProject: Project? = null
        set(value) {
            field = value
            updateSelectedProject()
        }

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

        initView()
        setupListeners()
    }

    private fun initView() {
        DatabaseManager.projectDao.getProjects().observe(viewLifecycleOwner) { data ->
            projects.clear()
            projects.addAll(data)

            if (selectedProject == null && data.isNotEmpty()) {
                selectedProject = data.first()
            }
        }
    }
    private fun setupListeners(){
        binding.btnSelectProject.setOnClickListener { selectProject() }
        binding.btnAdd.setOnClickListener { saveTask() }
    }
    private fun saveTask(){
        val name = binding.editText.text.toString()

        if (name.isEmpty()){
            Toast.makeText(requireContext(), "Type name", Toast.LENGTH_SHORT).show()
            binding.editText.showKeyboard(requireContext())
            return
        }
        val project  = selectedProject ?: return

        DatabaseManager.taskDao.addTask(
            task = Task(
                projectId = project.id,
                name = name,
                done = false
            )
        )
    }
    private fun selectProject(){

        val listItems = projects.map { it.name }.toTypedArray()

        AlertDialog.Builder(requireContext())
        .setTitle("Choose Project").setSingleChoiceItems(
                listItems, -1
        ){ _, which ->
            selectedProject = projects[which]
        }.setPositiveButton("Done") { dialog, _ ->
            dialog.dismiss()
        }.create().show()
    }
    private fun updateSelectedProject(){
        binding.tvSelectedProject.text = selectedProject?.name
    }

    private fun showEmptyError() {
        AlertDialog.Builder(requireContext()).setTitle("Error")
            .setMessage("Project's name should not be empty")
            .setPositiveButton("Ok"){ _, _ ->
                binding.editText.showKeyboard(requireContext())
            }
            .setIcon(R.drawable.baseline_error_24).show()
    }
}