package com.omstu.kvantorium.presentation.screens.registrationscreens.singuppersonaldatascreen

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.omstu.kvantorium.databinding.UserPersonalDataLayoutBinding
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel
import com.omstu.kvantorium.domain.datacontracts.model.UserSex
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.common.showError
import com.omstu.kvantorium.presentation.common.onDestroyNullable
import java.util.*

class SingUpPersonalDataFragment : BaseFragment() {
    companion object {
        fun newInstance(user: UserRegisterDataModel): Fragment {
            val args = Bundle().apply {
                putParcelable("USER_REGISTER_DATA_KEY", user)
            }

            val fragment = SingUpPersonalDataFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var binding by onDestroyNullable<UserPersonalDataLayoutBinding>()

    private val viewModel by lazy {
        ViewModelProvider(
            this, SingUpPersonalDataViewModelFactory(
                requireArguments().getParcelable("USER_REGISTER_DATA_KEY")!!
            )
        ).get(SingUpPersonalDataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserPersonalDataLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setNavigationVisibility(false)
        setObservers()
        setListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.setUserData()
    }

    private fun setObservers() {

        binding.apply {
            viewModel.apply {
                toolbarTitle.observe(viewLifecycleOwner) {
                    userFullName.text = it
                }

                emailError.observe(viewLifecycleOwner) {
                    editTextUserEmail.showError(it?.let { textId -> resources.getString(textId) }
                        ?: "")
                }

                phoneError.observe(viewLifecycleOwner) {
                    editTextUserPhoneNumber.showError(it?.let { textId -> resources.getString(textId) }
                        ?: "")
                }

                dateData.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) editTextUserBirthdate.editText!!.setText(it)
                }

                emailData.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) editTextUserEmail.editText!!.setText(it)
                }

                phoneData.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) editTextUserPhoneNumber.editText!!.setText(it)
                }

                userSexData.observe(viewLifecycleOwner) {
                    if (radioMale.text == it.value) radioMale.isChecked =
                        true else radioFemale.isChecked = true
                }

                birthdayText.observe(viewLifecycleOwner) {
                    editTextUserBirthdate.editText!!.setText(it)
                }

                birthdayDateToOpenDialog.observe(viewLifecycleOwner) {
                    if (it) {
                        DatePickerDialog(
                            requireContext(),
                            { _, year, month, dayOfMonth ->
                                setStartDate(year, month, dayOfMonth)
                            },
                            calendarBirthday.value!!.get(Calendar.YEAR),
                            calendarBirthday.value!!.get(Calendar.MONTH),
                            calendarBirthday.value!!.get(Calendar.DATE)
                        ).show()
                    }
                }
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            backButton.setOnClickListener { viewModel.navigateToPreviousScreen() }
            nextScreenButton.setOnClickListener {
                viewModel.validate(
                    editTextUserBirthdate.editText!!.text.toString(),
                    editTextUserEmail.editText!!.text.toString(),
                    editTextUserPhoneNumber.editText!!.text.toString(),
                    if (radioMale.isChecked) UserSex.MALE else UserSex.FEMALE
                )
            }

            editTextUserBirthdate.editText!!.apply {
                setOnClickListener { viewModel.openStartDateDialog() }
                setOnFocusChangeListener { _, _ -> viewModel.openStartDateDialog() }
            }
        }
    }
}