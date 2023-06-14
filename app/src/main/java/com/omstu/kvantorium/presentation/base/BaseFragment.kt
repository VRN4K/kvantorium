package com.omstu.kvantorium.presentation.base

import androidx.fragment.app.Fragment
import com.omstu.kvantorium.MainActivity
import com.omstu.kvantorium.domain.datacontracts.interfaces.BaseView

abstract class BaseFragment : Fragment(), BaseView {
    override fun setNavigationVisibility(isVisible: Boolean) {
        (activity as MainActivity).setNavigationVisibility(isVisible)
    }
}