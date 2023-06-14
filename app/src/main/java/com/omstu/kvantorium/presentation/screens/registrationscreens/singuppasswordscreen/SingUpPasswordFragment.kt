package com.omstu.kvantorium.presentation.screens.registrationscreens.singuppasswordscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.omstu.kvantorium.databinding.UserPasswordLayoutBinding
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.common.showError
import com.omstu.kvantorium.presentation.common.onDestroyNullable

class SingUpPasswordFragment : BaseFragment() {
    companion object {
        fun newInstance(user: UserRegisterDataModel): Fragment {
            val args = Bundle().apply {
                putParcelable("USER_REGISTER_DATA_KEY", user)
            }

            val fragment = SingUpPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var binding by onDestroyNullable<UserPasswordLayoutBinding>()

    private val viewModel by lazy {
        ViewModelProvider(
            this, SingUpPasswordDataViewModelFactory(
                requireArguments().getParcelable("USER_REGISTER_DATA_KEY")!!
            )
        ).get(SingUpPasswordDataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserPasswordLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.singUpButton.isEnabled = false
        setNavigationVisibility(false)
        setObservers()
        setListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setObservers() {
        binding.apply {
            viewModel.apply {
                userError.observe(viewLifecycleOwner) {
                    singUpButton.isEnabled = !it
                }

                currentPasswordError.observe(viewLifecycleOwner) {
                    editTextPassword.showError(it?.let { textId -> resources.getString(textId) }
                        ?: "")
                }

                confirmPasswordError.observe(viewLifecycleOwner) {
                    editTextConfirmPassword.showError(it?.let { textId -> resources.getString(textId) }
                        ?: "")
                }
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            backButton.setOnClickListener { viewModel.navigateToPreviousScreen() }
            singUpButton.setOnClickListener {
                viewModel.onRegisterButtonClick(
                    editTextPassword.editText!!.text.toString(),
                    editTextConfirmPassword.editText!!.text.toString(),
                )
            }

            editTextPassword.editText!!.addTextChangedListener {
                viewModel.confirmUserPasswordsData(
                    editTextPassword.editText!!.text.toString(),
                    editTextConfirmPassword.editText!!.text.toString(),
                    personalData.isChecked
                )
            }

            editTextConfirmPassword.editText!!.addTextChangedListener {
                viewModel.confirmUserPasswordsData(
                    editTextPassword.editText!!.text.toString(),
                    editTextConfirmPassword.editText!!.text.toString(),
                    personalData.isChecked
                )
            }

            personalData.setOnCheckedChangeListener { _, _ ->
                viewModel.confirmUserPasswordsData(
                    editTextPassword.editText!!.text.toString(),
                    editTextConfirmPassword.editText!!.text.toString(),
                    personalData.isChecked
                )
            }
        }
    }
}