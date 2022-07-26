package com.yb.part5_chapter05.presenter

interface BaseView<PresenterT : BasePresenter> {

    val presenter: PresenterT
}