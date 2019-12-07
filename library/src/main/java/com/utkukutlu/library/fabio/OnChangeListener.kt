package com.utkukutlu.library.fabio

interface OnChangeListener {
    fun onMainSelected(): Boolean
    fun onToggleChanged(isOpen: Boolean)
}
