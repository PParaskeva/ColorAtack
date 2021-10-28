package com.exampe.coloratack.android.gameFragment.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.exampe.coloratack.android.extentions.screenDimensions
import com.exampe.coloratack.pokos.Row


class BoardRow : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    private val mCells = mutableListOf<Pair<Int, BorderCell>>()

    var row: Row? = null
        set(value) {
            if (field != value) {
                field = value
                mCells.forEach { pair ->
                    field?.cells?.get(pair.first)?.let {
                        pair.second.color = it
                    }
                }
            }
        }

    var onCellClickListener: ((position: Int, row: Row) -> Unit)? = null

    init {
        orientation = HORIZONTAL
        context.screenDimensions { width, height ->
            val cellWidth = width / 6
            val cellHeight = height / 8
            repeat(6) { position ->
                val cell = BorderCell(context).apply {
                    layoutParams = FrameLayout.LayoutParams(cellWidth, cellHeight)
                    setOnClickListener {
                        row?.let { r ->
                            onCellClickListener?.invoke(position, r)
                        }
                    }
                }
                mCells.add(Pair(position, cell))
                addView(cell)
            }
        }

    }

}