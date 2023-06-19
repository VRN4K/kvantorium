package com.omstu.kvantorium.presentation.screens.updatephone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.omstu.kvantorium.databinding.UpdatePhoneLayoutBinding
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.common.onDestroyNullable
import com.omstu.kvantorium.presentation.common.showError
import com.omstu.kvantorium.presentation.screens.Screens

class UpdatePhoneFragment : BaseFragment() {
    private var binding by onDestroyNullable<UpdatePhoneLayoutBinding>()
    private val viewModel by viewModels<UpdatePhoneViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UpdatePhoneLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationVisibility(true)
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.backButton.setOnClickListener { viewModel.navigateToPreviousScreen() }
        binding.nextScreenButton.setOnClickListener {
            viewModel.validate(binding.editTextUserLastname.editText!!.text.toString())
        }
    }

    private fun setObservers() {
        viewModel.apply {
            profileData.observe(viewLifecycleOwner) {
                binding.apply {
                    binding.editTextUserLastname.editText!!.setText(it.userPhoneNumber)
                }
            }

            phoneError.observe(viewLifecycleOwner) {
                binding.editTextUserLastname.showError(it?.let { textId ->
                    resources.getString(
                        textId
                    )
                }
                    ?: "")
            }
        }
    }
}