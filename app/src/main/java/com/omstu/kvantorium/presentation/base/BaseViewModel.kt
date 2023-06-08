package com.omstu.kvantorium.presentation.base

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.omstu.kvantorium.presentation.screens.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), KoinComponent, CoroutineScope {
    protected val router: Router by inject()
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    open fun navigateFromOnboarding() {
        router.newRootScreen(Screens.getUnauthorizedUserMainFragment())
    }

    open fun navigateTo(screen: FragmentScreen) {
        router.navigateTo(screen)
    }

    open fun navigateToPreviousScreen() {
        router.exit()
    }
}