package com.omstu.kvantorium.presentation.screens.singinscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.omstu.kvantorium.databinding.UserSingInLayoutBinding
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.common.showError
import com.omstu.kvantorium.presentation.screens.Screens
import com.omstu.kvantorium.presentation.common.onDestroyNullable

class SingInFragment : BaseFragment() {
    private var binding by onDestroyNullable<UserSingInLayoutBinding>()
    private val viewModel by viewModels<SingInViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserSingInLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.singInButton.isEnabled = false
        super.onViewCreated(view, savedInstanceState)
        setNavigationVisibility(false)
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            backButton.setOnClickListener { viewModel.navigateToPreviousScreen() }
            singInButton.setOnClickListener {
                viewModel.onAuthButtonClick(
                    editTextUserEmail.editText!!.text.toString(),
                    editTextPassword.editText!!.text.toString(),
                )
            }

            editTextUserEmail.editText!!.addTextChangedListener {
                viewModel.confirmUserDataFields(
                    editTextUserEmail.editText!!.text.toString(),
                    editTextPassword.editText!!.text.toString(),
                )
            }

            editTextPassword.editText!!.addTextChangedListener {
                viewModel.confirmUserDataFields(
                    editTextUserEmail.editText!!.text.toString(),
                    editTextPassword.editText!!.text.toString(),
                )
            }
        }
    }

    private fun setObservers() {
        binding.apply {
            viewModel.apply {
                userError.observe(viewLifecycleOwner) {
                    singInButton.isEnabled = !it
                }

                emailError.observe(viewLifecycleOwner) {
                    editTextPassword.showError(it?.let { textId -> resources.getString(textId) }
                        ?: "")
                }

                passwordError.observe(viewLifecycleOwner) {
                    editTextPassword.showError(it?.let { textId -> resources.getString(textId) }
                        ?: "")
                }

                userNotFoundError.observe(viewLifecycleOwner) {
                    editTextPassword.showError(it?.let { textId -> resources.getString(textId) }
                        ?: "")
                }
            }
        }
    }
}