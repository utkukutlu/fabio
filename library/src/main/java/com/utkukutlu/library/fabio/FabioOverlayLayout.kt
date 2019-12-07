package com.utkukutlu.library.fabio

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorInt

class FabioOverlayLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    @ColorInt
    private var mBackgroundColor: Int = Int.MIN_VALUE

    private var animationDuration: Long = 200

    init {
        hide(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = 4f
        }
    }

    fun setOpenBackgroundColor(@ColorInt color: Int) {
        mBackgroundColor = color
        updateBackground()
    }

    fun show(animate: Boolean) {
        toggle(true, animate)
    }

    fun hide(animate: Boolean) {
        toggle(false, animate)
    }

    fun toggle(show: Boolean, animate: Boolean) {
        val duration: Long = if (animate) {
            animationDuration
        } else {
            0
        }
        if (show) {
            showFade(this, duration)
        } else {
            hideFade(this, duration)
        }

    }

    private fun updateBackground() {
        setBackgroundColor(mBackgroundColor)
    }

}