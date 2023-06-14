package com.omstu.kvantorium.presentation.screens.newsscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.omstu.kvantorium.databinding.NewsScreenLayoutBinding
import com.omstu.kvantorium.domain.datacontracts.model.NewsDataModel
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.common.onDestroyNullable

class NewsFragment : BaseFragment() {
    companion object {
        fun newInstance(news: NewsDataModel): Fragment {
            val args = Bundle().apply {
                putParcelable("NEWS_DATA_KEY", news)
            }

            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var binding by onDestroyNullable<NewsScreenLayoutBinding>()

    private val viewModel by lazy {
        ViewModelProvider(
            this, NewsViewModelFactory(
                requireArguments().getParcelable("NEWS_DATA_KEY")!!
            )
        ).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsScreenLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setNavigationVisibility(false)
        setObservers()
        setListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setObservers() {
        binding.apply {
            viewModel.currentNews.observe(viewLifecycleOwner) {
                newsTitle.text = it.newsTitle
                newsImage.setImageDrawable(resources.getDrawable(it.largeNewsImage))
                newsText.text = it.newsText
            }
        }
    }

    private fun setListeners() {
        binding.backButton.setOnClickListener { viewModel.navigateToPreviousScreen() }
    }
}