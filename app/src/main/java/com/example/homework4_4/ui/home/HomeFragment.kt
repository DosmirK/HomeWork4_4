package com.example.homework4_4.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.homework4_4.R
import com.example.homework4_4.content.completed.CompletedTaskFragment
import com.example.homework4_4.content.longs.LongTaskFragment
import com.example.homework4_4.content.tasks.TaskFragment
import com.example.homework4_4.content.urgent.UrgentTaskFragment
import com.example.homework4_4.data.TaskDao
import com.example.homework4_4.data.TaskManager
import com.example.homework4_4.databinding.FragmentHomeBinding
import com.example.homework4_4.model.Tasks
import com.example.homework4_4.model.TypeOfTask
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskDao: TaskDao
    private lateinit var allTasks: List<Tasks>
    private lateinit var urgentTasks: List<Tasks>
    private lateinit var longTasks: List<Tasks>
    private lateinit var completedTasks: List<Tasks>

    private val fragments = listOf(
        TaskFragment(),
        UrgentTaskFragment(),
        LongTaskFragment(),
        CompletedTaskFragment()
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

        taskDao = TaskManager.dao
        loadData()
        initView()
        initListener()
    }

    private fun initListener() {
        binding.btnAddTask.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskEditFragment())
        }
    }

    private fun initView() {
        binding.homeViewPager.adapter =
            ViewPagerHomeAdapter(childFragmentManager, lifecycle, fragments)
        binding.homeViewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.homeViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> resources.getString(R.string.title_all_tasks)
                1 -> resources.getString(R.string.title_urgent_tasks)
                2 -> resources.getString(R.string.title_long_tasks)
                else -> resources.getString(R.string.title_completed_tasks)
            }
        }.attach()
    }

    private fun loadData() {
        taskDao.getAll().observe(viewLifecycleOwner, Observer { tasks ->
        allTasks = tasks
        urgentTasks = allTasks.filter { it.type == TypeOfTask.URGENT_TASKS }
        longTasks = allTasks.filter { it.type == TypeOfTask.LONG_TASKS }
        completedTasks = allTasks.filter { it.type == TypeOfTask.COMPLETED }

        urgentTasks = urgentTasks.sortedBy { it.type }
        longTasks = longTasks.sortedBy { it.type }
        completedTasks = completedTasks.sortedBy { it.type }

        (fragments[0] as TaskFragment).taskAdapter.submitList(allTasks)
        (fragments[1] as UrgentTaskFragment).taskAdapter.submitList(urgentTasks)
        (fragments[2] as LongTaskFragment).taskAdapter.submitList(longTasks)
        (fragments[3] as CompletedTaskFragment).taskAdapter.submitList(completedTasks)
    })
    }
}