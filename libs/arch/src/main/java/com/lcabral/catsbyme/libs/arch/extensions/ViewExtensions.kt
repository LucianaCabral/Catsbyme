package com.lcabral.catsbyme.libs.arch.extensions

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.reflect.KClass

fun <P : Parcelable> Bundle.toParcelable(key: String, clazz: KClass<P>): P? {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        @Suppress("DEPRECATION") getParcelable(key)
    } else getParcelable(key, clazz.java)
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
}
