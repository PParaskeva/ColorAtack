package com.exampe.coloratack.mvp.contracts

import com.exampe.coloratack.enums.Color
import com.exampe.coloratack.mvp.BasePresenter
import com.exampe.coloratack.mvp.BaseView
import com.exampe.coloratack.pokos.Row

interface GameContract {
    interface Presenter : BasePresenter {
        fun onCellClick(position: Int, row: Row)
        fun startGameLoop()
        fun stopGameLoop()
    }

    interface View : BaseView<Presenter> {
        fun setBoard(rows: List<Row>)
        fun setScore(score: Int)
        fun setSelectionColor(color: Color)
    }
}