package com.omstu.kvantorium.presentation.screens.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.omstu.kvantorium.databinding.MainScreenBinding
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.screens.mainscreen.adapters.CourseItemDataAdapter
import com.omstu.kvantorium.presentation.common.onDestroyNullable
import com.omstu.kvantorium.presentation.screens.Screens
import com.omstu.kvantorium.presentation.screens.mainscreen.adapters.NewsItemDataAdapter

class MainFragment : BaseFragment() {
    private var binding by onDestroyNullable<MainScreenBinding>()
    private val viewModel by viewModels<MainScreenViewModel>()

    private val courseItemsAdapter by lazy { CourseItemDataAdapter(resources) }
    private val newsItemsAdapter by lazy {
        NewsItemDataAdapter(resources, onViewItemClick = {
            viewModel.navigateTo(Screens.getNewsFragment(it))
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationVisibility(true)
        binding.coursesRecycler.adapter = courseItemsAdapter
        binding.newsRecycler.adapter = newsItemsAdapter
        setObservers()
    }

    private fun setObservers() {
        viewModel.coursesItems.observe(viewLifecycleOwner) {
            courseItemsAdapter.data = it
        }

        viewModel.newsItems.observe(viewLifecycleOwner) {
            newsItemsAdapter.data = it
        }

        viewModel.currentDate.observe(viewLifecycleOwner) {
            binding.currentDate.text = it
        }
    }
}