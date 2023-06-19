package com.omstu.kvantorium.presentation.screens.updateprofilescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.omstu.kvantorium.databinding.ProfileLayoutBinding
import com.omstu.kvantorium.databinding.UpdateProfileLayoutBinding
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.common.onDestroyNullable
import com.omstu.kvantorium.presentation.screens.Screens
import com.omstu.kvantorium.presentation.screens.profile.ProfileCourseItemDataAdapter
import com.omstu.kvantorium.presentation.screens.profile.ProfileViewModel

class UpdateProfileFragment : BaseFragment() {
    private var binding by onDestroyNullable<UpdateProfileLayoutBinding>()
    private val viewModel by viewModels<UpdateProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UpdateProfileLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationVisibility(true)

        setObservers()
        setListeners()
    }

    override fun onResume() {
        viewModel.setUserData()
        super.onResume()
    }

    private fun setListeners() {
        binding.backButton.setOnClickListener { viewModel.navigateToPreviousScreen() }
        binding.editPhone.setOnClickListener { viewModel.navigateTo(Screens.getPhoneUpdateFragment()) }
        binding.editEmail.setOnClickListener { viewModel.navigateTo(Screens.getEmailUpdateFragment()) }
    }

    private fun setObservers() {
        viewModel.apply {
            profileData.observe(viewLifecycleOwner) {
                binding.apply {
                    userFirstName.text = it.userFirstName
                    userLastName.text = it.userLastName
                    userEmail.text = it.userEmail
                    userPhone.text = it.userPhoneNumber
                    userBirthday.text = it.userBirthdayDate
                }
            }
        }
    }
}