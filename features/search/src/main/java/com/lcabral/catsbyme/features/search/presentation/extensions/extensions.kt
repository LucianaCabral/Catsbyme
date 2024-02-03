package com.lcabral.catsbyme.features.search.presentation.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.lcabral.catsbyme.features.breeds.R
import com.lcabral.catsbyme.libs.arch.extensions.showToast

fun Fragment.showErrorDialog(shouldShowError: Boolean, errorMessage: String?) {
    val dialogBuilder = MaterialAlertDialogBuilder(
        requireContext(),
        com.google.android.material.R.style.AlertDialog_AppCompat
    )
    dialogBuilder
        .setTitle(getString(R.string.search_error_connection))
        .setMessage(errorMessage)
        .setCancelable(false)
        .setNegativeButton(getString(R.string.button_close)) { dialog, _ ->
            dialog.dismiss()
        }.show()
    showToast(shouldShowError.toString())
}

internal fun TextInputLayout.errorState(@StringRes errorMessageRes: Int?) {
    error = errorMessageRes?.let { context.getString(it) }
}





