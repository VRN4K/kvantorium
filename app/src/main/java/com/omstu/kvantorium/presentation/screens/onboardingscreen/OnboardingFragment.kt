package com.omstu.kvantorium.presentation.screens.onboardingscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.omstu.kvantorium.databinding.OnboardingLayoutBinding
import com.omstu.kvantorium.presentation.base.BaseFragment

import ltst.nibirualert.my.presentation.common.onDestroyNullable

class OnboardingFragment : BaseFragment() {
    private var binding by onDestroyNullable<OnboardingLayoutBinding>()
    private val onboardingAdapter by lazy {
        OnboardingAdapter(::onSkipButtonClick)
    }
    private val onboardingButtonAdapter by lazy {
        OnboardingButtonAdapter { isLastItem ->
            if (isLastItem){
                viewModel.navigateFromOnboarding()
            }else{
                binding.onboardingPager.apply { currentItem += 1 }
            }
        }
    }
    private val viewModel by lazy { ViewModelProvider(this).get(OnboardingViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OnboardingLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            onboardingPager.adapter = onboardingAdapter
            onboardingButtonPager.adapter = onboardingButtonAdapter
        }

        viewModel.setOnboardingData()
        setObservers()
        setListeners()
        TabLayoutMediator(binding.intoTabLayout, binding.onboardingPager)
        { tab, position -> }.attach()

        TabLayoutMediator(
            binding.intoTabLayout,
            binding.onboardingButtonPager
        ) { tab, position -> }.attach()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setObservers() {
        viewModel.apply {
            onboardingData.observe(viewLifecycleOwner) {
                onboardingAdapter.items = it
            }

            onboardingButtonData.observe(viewLifecycleOwner) {
                onboardingButtonAdapter.items = it
            }
        }
    }

    private fun setListeners() {
        binding.onboardingButtonPager.isUserInputEnabled = false
        binding.onboardingPager.isUserInputEnabled = false

    }

    private fun onSkipButtonClick() {
        viewModel.navigateFromOnboarding()
    }
}
