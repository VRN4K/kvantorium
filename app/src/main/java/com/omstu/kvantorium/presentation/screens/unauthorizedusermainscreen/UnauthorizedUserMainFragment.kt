package com.omstu.kvantorium.presentation.screens.unauthorizedusermainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.omstu.kvantorium.databinding.UnauthorizedUserMainLayoutBinding
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.screens.Screens
import com.omstu.kvantorium.presentation.common.onDestroyNullable

class UnauthorizedUserMainFragment : BaseFragment() {
    private var binding by onDestroyNullable<UnauthorizedUserMainLayoutBinding>()

    private val viewModel by lazy { ViewModelProvider(this).get(UnauthorizedUserMainViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UnauthorizedUserMainLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setNavigationVisibility(false)
        setListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setListeners() {
        binding.singUpButton.setOnClickListener { viewModel.navigateTo(Screens.getSingUpUsernameFragment()) }
        binding.singInButton.setOnClickListener { viewModel.navigateTo(Screens.getSingInFragment()) }
    }
}