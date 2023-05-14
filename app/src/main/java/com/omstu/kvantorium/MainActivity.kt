package com.omstu.kvantorium

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
    private val navHolder: NavigatorHolder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getText(R.string.app_name)
        navHolder.setNavigator(AppNavigator(this, R.id.fragmentContainerView))
        mainActivityViewModel.openRootScreen()
    }
}