package com.omstu.kvantorium.presentation.screens.registrationscreens.singupusernamescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.omstu.kvantorium.databinding.UsernameSingUpLayoutBinding
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.screens.Screens
import com.omstu.kvantorium.presentation.common.onDestroyNullable

class SingUpUserNameFragment : BaseFragment() {
    private var binding by onDestroyNullable<UsernameSingUpLayoutBinding>()

    private val viewModel by lazy { ViewModelProvider(this).get(SingUpUserNameViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UsernameSingUpLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.nextScreenButton.isEnabled = false
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
                userError.observe(viewLifecycleOwner) {
                    nextScreenButton.isEnabled = !it
                }

                userFirstName.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) editTextUserName.editText!!.setText(it)
                }

                userLastName.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) editTextUserLastname.editText!!.setText(it)
                }
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            backButton.setOnClickListener { viewModel.navigateToPreviousScreen() }
            nextScreenButton.setOnClickListener {
                viewModel.navigateTo(
                    Screens.getSingUpPersonalDataFragment(
                        editTextUserName.editText!!.text.toString(),
                        editTextUserLastname.editText!!.text.toString()
                    )
                )
            }

            editTextUserLastname.editText!!.addTextChangedListener {
                viewModel.confirmUserNameData(
                    editTextUserName.editText!!.text.toString(),
                    editTextUserLastname.editText!!.text.toString()
                )
            }

            editTextUserName.editText!!.addTextChangedListener {
                viewModel.confirmUserNameData(
                    editTextUserName.editText!!.text.toString(),
                    editTextUserLastname.editText!!.text.toString()
                )
            }
        }
    }
}
