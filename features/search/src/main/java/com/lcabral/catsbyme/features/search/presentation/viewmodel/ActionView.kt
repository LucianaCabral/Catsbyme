package com.lcabral.catsbyme.features.search.presentation.viewmodel

internal sealed class ActionView {
    object HideKeyboard: ActionView()
    object ShowError: ActionView()
}