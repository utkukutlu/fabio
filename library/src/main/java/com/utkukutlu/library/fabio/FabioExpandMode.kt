package com.utkukutlu.library.fabio

import androidx.annotation.IntDef
import com.utkukutlu.library.fabio.FabioExpandMode.Companion.BOTTOM
import com.utkukutlu.library.fabio.FabioExpandMode.Companion.LEFT
import com.utkukutlu.library.fabio.FabioExpandMode.Companion.RIGHT
import com.utkukutlu.library.fabio.FabioExpandMode.Companion.TOP

@IntDef(TOP, RIGHT, BOTTOM, LEFT)
annotation class FabioExpandMode {
    companion object {
        const val TOP = 0
        const val RIGHT = 1
        const val BOTTOM = 2
        const val LEFT = 3
    }
}