package com.utkukutlu.library.fabio

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Fabio @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val fabio = this

    private var mLayoutAttrs: TypedArray =
        context.theme.obtainStyledAttributes(attrs, R.styleable.Fabio, defStyleAttr, defStyleAttr)


    private val EMPTY_RESOURCE = Int.MIN_VALUE

    private var mainFab: FloatingActionButton? = null

    private var isOpen = false

    @DrawableRes
    private var openIcon: Int = EMPTY_RESOURCE
    @DrawableRes
    private var closeIcon: Int = EMPTY_RESOURCE

    //    @ColorRes
    private var openBackgroundColor = EMPTY_RESOURCE
    //    @ColorRes
    private var closeBackgroundColor = EMPTY_RESOURCE

    private var mOnChangeListener: OnChangeListener? = null
    private var mOnFabSelectedListener: OnFabSelectedListener? = null

    private var mSubFabs: ArrayList<FabioItem> = arrayListOf()

    private var mExpandMode: Int = EMPTY_RESOURCE

    @FloatingActionButton.Size
    private var mainFabSize: Int = FloatingActionButton.SIZE_NORMAL

    @IdRes
    private var mFabioOverlayLayoutId: Int = EMPTY_RESOURCE

    private var mFabioOverlayLayout: FabioOverlayLayout? = null

    private var animationDuration: Long = 300

    //    @ColorRes
    private var mOverlayLayoutBackground: Int = EMPTY_RESOURCE

    private var mOrientation: Int = LinearLayout.HORIZONTAL

    init {
        layoutAttrs()
        setup()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (mFabioOverlayLayout == null) {
            val overlayLayout = rootView.findViewById<FabioOverlayLayout>(mFabioOverlayLayoutId)
            setOverlayLayout(overlayLayout)
        }
    }

    private fun setOverlayLayout(layout: FabioOverlayLayout?) {
        mFabioOverlayLayout = layout
        if (mOverlayLayoutBackground != EMPTY_RESOURCE) {
            mFabioOverlayLayout?.setOpenBackgroundColor(mOverlayLayoutBackground)
        }
    }

    private fun layoutAttrs() {
        if (openIcon == EMPTY_RESOURCE) {
            openIcon = mLayoutAttrs.getResourceId(R.styleable.Fabio_fbOpenIcon, EMPTY_RESOURCE)
        }
        if (closeIcon == EMPTY_RESOURCE) {
            closeIcon = mLayoutAttrs.getResourceId(R.styleable.Fabio_fbCloseIcon, EMPTY_RESOURCE)
        }
        if (openBackgroundColor == EMPTY_RESOURCE) {
            openBackgroundColor =
                mLayoutAttrs.getColor(R.styleable.Fabio_fbOpenColor, EMPTY_RESOURCE)
        }
        if (closeBackgroundColor == EMPTY_RESOURCE) {
            closeBackgroundColor =
                mLayoutAttrs.getColor(R.styleable.Fabio_fbCloseColor, EMPTY_RESOURCE)
        }
        if (mExpandMode == EMPTY_RESOURCE) {
            mExpandMode = mLayoutAttrs.getInt(R.styleable.Fabio_fbExpandMode, FabioExpandMode.TOP)
            mOrientation =
                if (mExpandMode == FabioExpandMode.TOP || mExpandMode == FabioExpandMode.BOTTOM) {
                    LinearLayout.VERTICAL
                } else if (mExpandMode == FabioExpandMode.LEFT || mExpandMode == FabioExpandMode.RIGHT) {
                    LinearLayout.HORIZONTAL
                } else {
                    LinearLayout.VERTICAL
                }
        }
        if (mFabioOverlayLayoutId == EMPTY_RESOURCE) {
            mFabioOverlayLayoutId =
                mLayoutAttrs.getResourceId(R.styleable.Fabio_fbOverlayLayout, EMPTY_RESOURCE)
        }

        if (mOverlayLayoutBackground == EMPTY_RESOURCE) {
            mOverlayLayoutBackground =
                mLayoutAttrs.getColor(R.styleable.Fabio_fbOverlayColor, EMPTY_RESOURCE)
        }

        animationDuration = mLayoutAttrs.getInt(R.styleable.Fabio_fbAnimationDuration, 300).toLong()
    }

    private fun setup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = 6f
        }
        orientation = mOrientation
        mainFab = createMain()
        updateMainFabIcon()
        updateMainFabBackground()

        if (mOverlayLayoutBackground != EMPTY_RESOURCE) {
            mFabioOverlayLayout?.setOpenBackgroundColor(mOverlayLayoutBackground)
        }

//        mFabioOverlayLayout.addView(mainFab)
        addView(mainFab)
    }

    private fun createMain(): FloatingActionButton {
        val floatingActionButton = FloatingActionButton(context)
        val layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER
        layoutParams.weight = 1f
//        android:animateLayoutChanges="true"
//        android:animationCache="true"

        val marginHorizontal = 4.dp
        val marginVertical = 4.dp
        layoutParams.setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical)
        floatingActionButton.useCompatPadding = true
        floatingActionButton.layoutParams = layoutParams
        floatingActionButton.isClickable = true
        floatingActionButton.isFocusable = true
        floatingActionButton.size = mainFabSize
//        floatingActionButton.setBackgroundResource(mainFabBackground)
        floatingActionButton.setOnClickListener {
            if (isOpen) {
                if (mOnChangeListener == null || mOnChangeListener?.onMainSelected() == false) {
                    close()
                }
            } else {
                open()
            }
        }

        return floatingActionButton
    }


    private fun createSubFab() {
        mSubFabs.forEach { mFabioItem ->
            //            val subFab = FloatingActionButton(context)
//            val layoutParams = LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//            layoutParams.gravity = Gravity.END
//            val marginHorizontal = 4.dp
//            val marginVertical = 4.dp
//            layoutParams.setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical)
//            subFab.useCompatPadding = true
//            subFab.layoutParams = layoutParams
//            subFab.isClickable = true
//            subFab.isFocusable = true
//            subFab.size = mFabioItem.size
////            subFab.setBackgroundResource(mFabioItem.background)
//            subFab.setImageDrawable(context.getDrawableByInt(mFabioItem.icon))
//            subFab.backgroundTintList = ColorStateList.valueOf(mFabioItem.background)
//            subFab.visibility = View.GONE
            addView(mFabioItem, getSubLayoutPos())
        }
    }

//    fun setFabOpenIcon(@DrawableRes icon: Int) {
//        openIcon = icon
//        updateMainFabIcon()
//    }

//    fun setFabCloseIcon(@DrawableRes icon: Int) {
//        closeIcon = icon
//        updateMainFabIcon()
//    }

    fun close(animate: Boolean = true) {
        toggle(false, animate)
    }

    fun open(animate: Boolean = true) {
        toggle(true, animate)
    }

    fun toggle(show: Boolean, animate: Boolean = true) {
        isOpen = show

        mainFab?.let {
            rotate(it, 200)
        }
        if (show) {
            mOnChangeListener?.onMainSelected()
            for (i in 0..mSubFabs.size) {
                if (getChildAt(i).javaClass.simpleName == FabioItem::class.java.simpleName) {
                    if (animate) {
                        if (mExpandMode == FabioExpandMode.TOP) {
                            context.slideInBottom(getChildAt(i), animationDuration)
                        } else if (mExpandMode == FabioExpandMode.LEFT) {
                            context.slideInRight(getChildAt(i), animationDuration)
                        }
                    } else {
                        getChildAt(i).visibility = View.VISIBLE
                    }
                }
            }
        } else {
            for (i in 0..mSubFabs.size) {
                if (getChildAt(i).javaClass.simpleName == FabioItem::class.java.simpleName) {

                    if (animate) {
                        if (mExpandMode == FabioExpandMode.TOP) {
                            context.slideOutBottom(getChildAt(i), animationDuration)
                        } else if (mExpandMode == FabioExpandMode.LEFT) {
                            context.slideOutRight(getChildAt(i), animationDuration)
                        }
                    } else {
                        getChildAt(i).visibility = View.GONE
                    }
                }
            }
        }

        mFabioOverlayLayout?.toggle(show, animate)
        updateMainFabBackground()
        updateMainFabIcon()


        mOnChangeListener?.onToggleChanged(show)
    }

    fun hide() {
        fabio.visibility = View.GONE
    }

    fun show() {
        fabio.visibility = View.VISIBLE
    }

    fun setOnActionSelectedListener(listener: OnFabSelectedListener) {
        mOnFabSelectedListener = listener
    }

    fun setOnChangeListener(onChangeListener: OnChangeListener) {
        mOnChangeListener = onChangeListener
    }

    fun setSubFabs(items: ArrayList<FabioItem>) {
        mSubFabs = items.cloneArrayList()
        createSubFab()
    }

    fun setFabSize(@FloatingActionButton.Size size: Int) {
        mainFabSize = size
    }

    private fun updateMainFabIcon() {
        if (isOpen) {
            mainFab?.setImageDrawable(context.getDrawableByInt(closeIcon))
        } else {
            mainFab?.setImageDrawable(context.getDrawableByInt(openIcon))
        }
    }

    private fun updateMainFabBackground() {
        if (isOpen) {
            if (openBackgroundColor != EMPTY_RESOURCE) {
                mainFab?.backgroundTintList =
                    ColorStateList.valueOf(closeBackgroundColor)
            }
        } else {
            if (openBackgroundColor != EMPTY_RESOURCE) {
                mainFab?.backgroundTintList =
                    ColorStateList.valueOf(openBackgroundColor)
            }
        }
    }

    private fun getSubLayoutPos(): Int {
        var pos = 0
        if (mSubFabs.isNullOrEmpty()) {
            pos = mSubFabs.size - 1
        }
        return pos
    }


}

