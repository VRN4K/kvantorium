package com.omstu.kvantorium.domain.datacontracts.utils

import com.omstu.kvantorium.presentation.common.exceptions.CommonExceptionHandler
import kotlinx.coroutines.*

fun CoroutineScope.launchUI(callback: suspend () -> Unit) =
    launch(Dispatchers.Main) { callback() }

fun CoroutineScope.launchUI(handler: CoroutineExceptionHandler, callback: suspend () -> Unit) =
    launch(Dispatchers.Main + handler) { callback() }

fun CoroutineScope.launchIO(callback: suspend () -> Unit) =
    launch(Dispatchers.IO) { callback() }

fun CoroutineScope.launchIO(handler: CommonExceptionHandler, callback: suspend () -> Unit) =
    launch(Dispatchers.IO + with(handler) {
        setCallback(callback)
        coroutineHandler
    }) { callback() }

suspend fun <T> withIO(callback: suspend () -> T) = withContext(Dispatchers.IO) { callback() }

suspend fun <T> withUI(callback: suspend () -> T) = withContext(Dispatchers.Main) { callback() }

fun <T> CoroutineScope.asyncIO(callback: suspend () -> T) = async(Dispatchers.IO) { callback() }
