package com.omstu.kvantorium

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.omstu.kvantorium.databinding.ActivityMainBinding
import com.omstu.kvantorium.domain.datacontracts.interfaces.BaseView
import com.omstu.kvantorium.presentation.common.setVisibility
import com.omstu.kvantorium.presentation.screens.Screens
import com.omstu.kvantorium.presentation.common.onDestroyNullable
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), BaseView {

    private val mainActivityViewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
    private val navHolder: NavigatorHolder by inject()
    private var binding by onDestroyNullable<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getText(R.string.app_name)
        navHolder.setNavigator(AppNavigator(this, R.id.fragmentContainerView))
        mainActivityViewModel.openRootScreen()
    }

    override fun setNavigationVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.setVisibility(isVisible)
    }
}

enum class NavBarItems(val menuId: Int, val screen: FragmentScreen) {
    HOME(R.id.navigation_item_main, Screens.getUnauthorizedUserMainFragment()),
    CALENDAR(R.id.navigation_item_calendar, Screens.getUnauthorizedUserMainFragment()),
    NOTIFICATION(R.id.navigation_item_notification, Screens.getUnauthorizedUserMainFragment()),
    PROFILE(R.id.navigation_item_profile, Screens.getUnauthorizedUserMainFragment())
}