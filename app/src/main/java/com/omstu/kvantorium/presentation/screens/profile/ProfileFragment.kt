package com.omstu.kvantorium.presentation.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.omstu.kvantorium.databinding.ProfileLayoutBinding
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.common.onDestroyNullable
import com.omstu.kvantorium.presentation.screens.Screens

class ProfileFragment : BaseFragment() {
    private var binding by onDestroyNullable<ProfileLayoutBinding>()
    private val viewModel by viewModels<ProfileViewModel>()

    private val courseItemsAdapter by lazy { ProfileCourseItemDataAdapter(resources) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationVisibility(true)
        binding.courseRecycler.adapter = courseItemsAdapter
        setObservers()
        setListeners()
    }

    override fun onResume() {
        viewModel.setUserData()
        super.onResume()
    }

    private fun setListeners() {
        binding.exitButton.setOnClickListener { viewModel.onExitButtonClick() }
        binding.editButton.setOnClickListener { viewModel.navigateTo(Screens.getUpdateProfileScreenFragment()) }
    }

    private fun setObservers() {
        viewModel.apply {
            coursesItems.observe(viewLifecycleOwner) {
                courseItemsAdapter.data = it
            }

            profileData.observe(viewLifecycleOwner) {
                binding.apply {
                    this.userFullName.text = "${it.userFirstName} ${it.userLastName}"
                    this.userEmail.text = it.userEmail
                    this.userPhone.text = it.userPhoneNumber
                }
            }
        }
    }
}