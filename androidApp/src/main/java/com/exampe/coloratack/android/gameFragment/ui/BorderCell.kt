package com.exampe.coloratack.android.gameFragment.ui

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.exampe.coloratack.android.R
import com.exampe.coloratack.android.extentions.getMColor
import com.exampe.coloratack.enums.Color

class BorderCell : FrameLayout {

    private val mImage = ImageView(context)

    var color: Color = Color.WHITE
        set(value) {
            if (field != value) {
                field = value
                mImage.setBackgroundColor(context.getMColor(field))
            }
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        setBackgroundResource(R.drawable.border)
        buildImage()
        addView(mImage)
    }

    private fun buildImage() {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        mImage.apply {
            layoutParams = params
        }
    }

}