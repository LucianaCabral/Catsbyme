package com.lcabral.catsbyme.libs.arch.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showError() {
    val materialAlertDialogBuilder =
        MaterialAlertDialogBuilder(
            requireContext(),
            R.style.AlertDialog_AppCompat
        )
    materialAlertDialogBuilder
        .setTitle("Opa! Ocorreu um erro")
        .setMessage("Aguarde")
        .setNegativeButton("Cancelar") { dialog, which ->
            showToast("cancelado")
        }
    materialAlertDialogBuilder.show()
}
