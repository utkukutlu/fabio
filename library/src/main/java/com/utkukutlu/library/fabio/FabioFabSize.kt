package com.utkukutlu.library.fabio

import androidx.annotation.IntDef
import com.utkukutlu.library.fabio.FabioFabSize.Companion.MINI
import com.utkukutlu.library.fabio.FabioFabSize.Companion.NORMAL

@IntDef(NORMAL,MINI)
annotation class FabioFabSize {
    companion object {
        const val NORMAL= 0
        const val MINI = 1
    }
}