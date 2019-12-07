package com.utkukutlu.library.fabio

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabioSubFab @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    private fun createMain(): FloatingActionButton {
        val floatingActionButton = FloatingActionButton(context)
        val layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER
        val marginHorizontal = 4.dp
        val marginVertical = 4.dp
        layoutParams.setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical)
        floatingActionButton.useCompatPadding = true
        floatingActionButton.layoutParams = layoutParams
        floatingActionButton.isClickable = true
        floatingActionButton.isFocusable = true
//        floatingActionButton.size = mainFabSize
        floatingActionButton.setOnClickListener {
            //            if (isOpen) {
//                if (mOnChangeListener == null || mOnChangeListener?.onMainSelected() == false && true) {
//                    close()
//                }
//            } else {
//                open()
//            }
        }

        return floatingActionButton
    }

}