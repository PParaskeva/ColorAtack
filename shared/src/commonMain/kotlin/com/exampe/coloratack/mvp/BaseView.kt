package com.exampe.coloratack.mvp

interface BaseView<P> {
    fun setPresenter(presenter: P)
}