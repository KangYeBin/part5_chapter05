package com.yb.part5_chapter05.presentation.stationarrivals

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yb.part5_chapter05.databinding.ItemArrivalBinding
import com.yb.part5_chapter05.domain.ArrivalInformation

class StationArrivalsAdapter : RecyclerView.Adapter<StationArrivalsAdapter.ViewHolder>() {

    var data: List<ArrivalInformation> = emptyList()

    inner class ViewHolder(private val binding: ItemArrivalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(arrival: ArrivalInformation) {
            binding.apply {
                labelTextView.badgeColor = arrival.subway.color
                labelTextView.text = "${arrival.subway.label} - ${arrival.direction}"
                arrivalMessageTextView.text = arrival.message
                arrivalMessageTextView.setTextColor(
                    if (arrival.message.contains("당역")) Color.RED
                    else Color.DKGRAY)
                destinationTextView.text = "\uD83D\uDEA9 ${arrival.destination}"
                updatedTimeTextView.text = "측정 시간 : ${arrival.updatedAt}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemArrivalBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}