package com.utkukutlu.library.fabio

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat


fun <T> ArrayList<T>.cloneArrayList(): ArrayList<T> {
    val mArray = arrayListOf<T>()
    this.forEach {
        mArray.add(it)
    }
    return mArray
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f)

fun Context.getDrawableByInt(dr: Int): Drawable? {
    return AppCompatResources.getDrawable(this, dr)
}

//fun Context.getColorByInt(dr: Int): Color? {
////    return ContextCompat.getColor(this, dr)
//}


fun showFade(view: View, duration: Long = 1000) {
    view.visibility = View.GONE
    val anim = AlphaAnimation(0f, 1f)
    anim.duration = duration
    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            view.visibility = View.VISIBLE
        }

        override fun onAnimationStart(animation: Animation?) {
        }

    })
    view.startAnimation(anim)
}

fun hideFade(view: View, duration: Long = 1000) {
    val anim = AlphaAnimation(1f, 0f)
    anim.duration = duration
    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            view.visibility = View.GONE
        }

        override fun onAnimationStart(animation: Animation?) {
        }

    })
    view.startAnimation(anim)
}

fun rotate(view: View, duration: Long = 500) {
    val anim = RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    anim.duration = duration
    anim.isFillEnabled = true
    anim.fillAfter = true
    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            view.rotation = 180f
        }

        override fun onAnimationStart(animation: Animation?) {
        }

    })
    view.startAnimation(anim)
}

fun Context.slideInBottom(view: View, duration: Long = 1000) {
    val anim = AnimationUtils.loadAnimation(
        this,
        R.anim.slide_in_bottom
    )
    anim.duration = duration
    view.startAnimation(anim)
    view.visibility = View.VISIBLE
}

fun Context.slideOutBottom(view: View, duration: Long = 1000) {
    val anim = AnimationUtils.loadAnimation(
        this,
        R.anim.slide_out_bottom
    )
    anim.duration = duration
    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            view.visibility = View.GONE
        }

        override fun onAnimationStart(animation: Animation?) {
        }

    })
    view.startAnimation(anim)
}


fun Context.slideInRight(view: View, duration: Long = 1000) {
    val anim = AnimationUtils.loadAnimation(
        this,
        R.anim.slide_in_right
    )
    anim.duration = duration
    view.startAnimation(anim)
    view.visibility = View.VISIBLE
}

fun Context.slideOutRight(view: View, duration: Long = 1000) {
    val anim = AnimationUtils.loadAnimation(
        this,
        R.anim.slide_out_right
    )
    anim.duration = duration
    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            view.visibility = View.GONE
        }

        override fun onAnimationStart(animation: Animation?) {
        }

    })
    view.startAnimation(anim)
}
