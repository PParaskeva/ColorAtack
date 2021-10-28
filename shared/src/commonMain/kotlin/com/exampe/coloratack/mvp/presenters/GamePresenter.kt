package com.exampe.coloratack.mvp.presenters

import com.exampe.coloratack.enums.Color
import com.exampe.coloratack.enums.Color.*
import com.exampe.coloratack.mvp.contracts.GameContract
import com.exampe.coloratack.pokos.Row
import kotlinx.coroutines.*

class GamePresenter(val mView: GameContract.View?) : GameContract.Presenter {

    private var mScore: Int = 0
    private val mIsGameOver: Boolean = false
    private var mLv: Int = 1
    private var mTimer: Long = 1000L
    private var mCurrentColor: Color = BLUE
    private var mGameLoop: Job? = null

    private var mRows = listOf(
        Row(0, listOf(WHITE, WHITE, RED, WHITE, WHITE, WHITE)),
        Row(1, listOf(WHITE, WHITE, WHITE, WHITE, WHITE, WHITE)),
        Row(2, listOf(WHITE, WHITE, WHITE, WHITE, WHITE, WHITE)),
        Row(3, listOf(WHITE, WHITE, WHITE, WHITE, WHITE, WHITE)),
        Row(4, listOf(WHITE, WHITE, WHITE, WHITE, WHITE, WHITE)),
        Row(5, listOf(WHITE, WHITE, WHITE, WHITE, WHITE, WHITE)),
        Row(6, listOf(WHITE, WHITE, WHITE, WHITE, WHITE, WHITE)),
        Row(7, listOf(WHITE, WHITE, WHITE, WHITE, WHITE, WHITE)),
    )

    override fun onCellClick(position: Int, row: Row) {

    }

    override fun startGameLoop() {
        mGameLoop = CoroutineScope(Dispatchers.Default).launch {
            while (!mIsGameOver) {
                delay(mTimer)
                mRows =
                    listOf(Row(0, listOf(WHITE, WHITE, WHITE, WHITE, WHITE, WHITE))) + mRows.take(
                        mRows.size - 1
                    ).mapIndexed { index: Int, row: Row ->
                        row.copy(id = index + 1)
                    }
                mView?.setBoard(mRows)
            }
        }
    }

    override fun stopGameLoop() {
        mGameLoop?.cancel()
        mGameLoop = null
    }

    override fun start() {
        mView?.setPresenter(this)
        mView?.setBoard(mRows)
    }
}