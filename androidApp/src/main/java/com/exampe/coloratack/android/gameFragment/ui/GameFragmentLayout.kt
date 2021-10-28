package com.exampe.coloratack.android.gameFragment.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exampe.coloratack.android.extentions.dpToPx
import com.exampe.coloratack.android.gameFragment.adapters.BoardAdapter
import com.exampe.coloratack.enums.Color
import com.exampe.coloratack.pokos.Row

class GameFragmentLayout : FrameLayout {

    private val mBoardLayout: FrameLayout = FrameLayout(context)
    private val mRecyclerViewBoard: RecyclerView = RecyclerView(context)

    private val mBoardAdapter: BoardAdapter by lazy {
        BoardAdapter(onCellClickListener)
    }

    private val mDp_30 = context.dpToPx(30)

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

    var rows: List<Row> = listOf()
        set(value) {
            field = value
            mBoardAdapter.rows = rows
        }

    var selectedColor: Color = Color.WHITE

    var onCellClickListener: ((position: Int, row: Row) -> Unit)? = null

    init {
        setUpBoardLayout()
        setUpRecyclerViewBoard()

        mBoardLayout.addView(mRecyclerViewBoard)
        addView(mBoardLayout)
    }

    private fun setUpBoardLayout() {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        mBoardLayout.apply {
            layoutParams = params
        }
    }

    private fun setUpRecyclerViewBoard() {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
            setMargins(mDp_30, mDp_30, mDp_30, mDp_30)
        }
        mRecyclerViewBoard.apply {
            layoutParams = params
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
            adapter = mBoardAdapter
        }
    }
}