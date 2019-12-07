package com.utkukutlu.library.fabio

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FabioItem : LinearLayout {

    @DrawableRes
    var icon: Int = Int.MIN_VALUE

    var background: Int = Int.MIN_VALUE

    @FabioFabSize
    var size: Int = FloatingActionButton.SIZE_NORMAL

    constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : super(context, attrs, defStyleAttr)

    constructor(
        context: Context,
        icon: Int,
        background: Int,
        size: Int = FloatingActionButton.SIZE_NORMAL,
        defStyleAttr: Int = 0,
        attrs: AttributeSet? = null
    ) : super(context, attrs, defStyleAttr) {
        this.icon = icon
        this.background = background
        this.size = size

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = 6f
        }

        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER
        layoutParams.weight = 1f
        this.layoutParams = layoutParams
        addView(createMain())
    }

    private fun createMain(): FloatingActionButton {
        val floatingActionButton = FloatingActionButton(context)
        val layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER
        layoutParams.weight = 1f

        val marginHorizontal = 8.dp
        val marginVertical = 8.dp
        layoutParams.setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical)
        floatingActionButton.useCompatPadding = false
        floatingActionButton.layoutParams = layoutParams
        floatingActionButton.isClickable = true
        floatingActionButton.isFocusable = true
        floatingActionButton.size = size
        floatingActionButton.setOnClickListener {
            //            if (isOpen) {
//                if (mOnChangeListener == null || mOnChangeListener?.onMainSelected() == false && true) {
//                    close()
//                }
//            } else {
//                open()
//            }
        }
        floatingActionButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, background))
        floatingActionButton.setImageDrawable(context.getDrawableByInt(icon))

        visibility = View.GONE
        return floatingActionButton
    }

}