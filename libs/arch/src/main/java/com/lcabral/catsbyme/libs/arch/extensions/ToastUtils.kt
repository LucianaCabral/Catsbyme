package com.lcabral.catsbyme.libs.arch.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(string: String) {
    Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToastSecond(boolean:Boolean, string: String?,) {
    Toast.makeText(requireContext(), string + boolean, Toast.LENGTH_SHORT).show()
}
