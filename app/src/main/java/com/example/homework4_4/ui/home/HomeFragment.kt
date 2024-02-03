package com.example.homework4_4.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.homework4_4.R
import com.example.homework4_4.content.studies.StudiesProjectFragment
import com.example.homework4_4.content.work.WorkProjectsFragment
import com.example.homework4_4.content.projects.ProjectFragment
import com.example.homework4_4.content.my_project.MyProjectFragment
import com.example.homework4_4.data.dao.ProjectDao
import com.example.homework4_4.data.db.DatabaseManager
import com.example.homework4_4.data.model.Project
import com.example.homework4_4.data.model.ProjectAndTasks
import com.example.homework4_4.data.model.TypeOfProject
import com.example.homework4_4.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var projectDao: ProjectDao
    private lateinit var allProjects: List<Project>
    private lateinit var myProjects: List<Project>
    private lateinit var workProjects: List<Project>
    private lateinit var studiesProject: List<Project>

    private val fragments = listOf(
        ProjectFragment(),
        MyProjectFragment(),
        WorkProjectsFragment(),
        StudiesProjectFragment()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectDao = DatabaseManager.projectDao
        loadData()
        initView()
        initListener()
    }

    private fun initListener() {
        binding.btnAddTask.setOnClickListener {

            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToEditProjectFragment())
        }
    }

    private fun initView() {
        binding.homeViewPager.adapter =
            ViewPagerHomeAdapter(childFragmentManager, lifecycle, fragments)
        binding.homeViewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.homeViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> resources.getString(R.string.title_all_tasks)
                1 -> resources.getString(R.string.my_project)
                2 -> resources.getString(R.string.work_project)
                else -> resources.getString(R.string.studies_project)
            }
        }.attach()
    }

    private fun loadData() {
        projectDao.getProjects().observe(viewLifecycleOwner, Observer { projects ->
        allProjects = projects
        myProjects = allProjects.filter { it.type == TypeOfProject.MY_PROJECT }
        workProjects = allProjects.filter { it.type == TypeOfProject.WORK }
        studiesProject = allProjects.filter { it.type == TypeOfProject.STUDIES }

        myProjects = myProjects.sortedBy { it.type }
        workProjects = workProjects.sortedBy { it.type }
        studiesProject = studiesProject.sortedBy { it.type }

        (fragments[0] as ProjectFragment).projectAdapter.submitList(allProjects)
        (fragments[1] as MyProjectFragment).projectAdapter.submitList(myProjects)
        (fragments[2] as WorkProjectsFragment).projectAdapter.submitList(workProjects)
        (fragments[3] as StudiesProjectFragment).projectAdapter.submitList(studiesProject)
    })
    }
}