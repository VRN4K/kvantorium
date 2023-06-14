package com.omstu.kvantorium.presentation.common.exceptions

import kotlinx.coroutines.*
import com.omstu.kvantorium.domain.datacontracts.utils.launchUI

class CommonExceptionHandler(
    private val onException: ((Throwable) -> Unit)? = null
) : CoroutineScope {

    private var callback: (suspend () -> Unit)? = null

    fun setCallback(callback: (suspend () -> Unit)? = null) {
        this.callback = callback
    }

    val coroutineHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        launchUI {
            throwable.printStackTrace()
            when (throwable) {
                else -> onException?.invoke(throwable)
            }
        }
    }
    override val coroutineContext = coroutineHandler
}


fun createExceptionHandler(onException: (Throwable) -> Unit) =
    CommonExceptionHandler(onException)
