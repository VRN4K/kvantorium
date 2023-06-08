package com.omstu.kvantorium.presentation.common

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.android.material.textfield.TextInputLayout



fun TextInputLayout.showError(errorText: String = "") {
    this.error = errorText
}

fun TextInputLayout.showHelper(helperText: String = "") {
    this.helperText = helperText
}

fun View.setVisibility(isVisible: Boolean = true) {
    this.visibility = if (isVisible) VISIBLE else GONE
}

fun Any?.toEmptyOnNullString() = this?.toString() ?: ""




