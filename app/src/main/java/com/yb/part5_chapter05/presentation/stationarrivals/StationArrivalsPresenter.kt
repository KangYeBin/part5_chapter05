package com.yb.part5_chapter05.presentation.stationarrivals

import com.yb.part5_chapter05.data.repository.StationRepository
import com.yb.part5_chapter05.domain.Station
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class StationArrivalsPresenter(
    private val view: StationArrivalsContract.View,
    private val stationRepository: StationRepository,
    private val station: Station,
) : StationArrivalsContract.Presenter {

    override val scope = MainScope()

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onViewCreated() {
        fetchStationArrivals()
    }

    override fun onDestroyView() {
    }

    override fun fetchStationArrivals() {
        scope.launch {
            try {
                view.showLoadingIndicator()
                view.showStationArrivals(stationRepository.getStationArrivals(station.stationName))

            } catch (exception: Exception) {
                exception.printStackTrace()
                view.showErrorDescription(exception.message ?: "알 수 없는 문제가 발생했어요")

            } finally {
                view.hideLoadingIndicator()
            }
        }
    }

}