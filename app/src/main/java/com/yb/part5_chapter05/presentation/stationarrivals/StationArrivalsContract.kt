package com.yb.part5_chapter05.presentation.stationarrivals

import com.yb.part5_chapter05.domain.ArrivalInformation
import com.yb.part5_chapter05.presentation.BasePresenter
import com.yb.part5_chapter05.presentation.BaseView
import com.yb.part5_chapter05.presentation.stations.StationsContract

interface StationArrivalsContract {

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showStationArrivals(arrivalInformation: List<ArrivalInformation>)
    }

    interface Presenter : BasePresenter {
        fun fetchStationArrivals()

        fun toggleStationFavorite()
    }
}