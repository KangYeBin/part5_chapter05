package com.yb.part5_chapter05.presentation.stationarrivals

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yb.part5_chapter05.R
import com.yb.part5_chapter05.databinding.FragmentStationArrivalsBinding
import com.yb.part5_chapter05.domain.ArrivalInformation
import com.yb.part5_chapter05.extension.toGone
import com.yb.part5_chapter05.extension.toVisible
import org.koin.android.scope.ScopeFragment
import org.koin.core.parameter.parametersOf

class StationArrivalsFragment : ScopeFragment(), StationArrivalsContract.View {

    private var binding: FragmentStationArrivalsBinding? = null

    private val arguments: StationArrivalsFragmentArgs by navArgs()

    override val presenter: StationArrivalsContract.Presenter by inject { parametersOf(arguments.station) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentStationArrivalsBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
        presenter.onViewCreated()
    }

    override fun showLoadingIndicator() {
        binding?.progressBar?.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.toGone()
    }

    override fun showErrorDescription(message: String) {
        binding?.apply {
            recyclerView.toGone()
            errorDescriptionTextView.toVisible()
            errorDescriptionTextView.text = message
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showStationArrivals(arrivalInformation: List<ArrivalInformation>) {
        binding?.apply {
            errorDescriptionTextView.toGone()
            (recyclerView.adapter as StationArrivalsAdapter).run {
                this.data = arrivalInformation
                notifyDataSetChanged()
            }
        }
    }

    private fun initViews() {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = StationArrivalsAdapter()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_station_arrivals, menu)
                menu.findItem(R.id.favoriteAction).apply {
                    setIcon(
                        if (arguments.station.isFavorited) {
                            R.drawable.ic_star
                        } else {
                            R.drawable.ic_star_empty
                        }
                    )
                    isChecked = arguments.station.isFavorited
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.refreshAction -> {
                        presenter.fetchStationArrivals()
                        true
                    }
                    R.id.favoriteAction -> {
                        menuItem.isChecked = !menuItem.isChecked

                        menuItem.setIcon(
                            if (menuItem.isChecked) {
                                R.drawable.ic_star
                            } else {
                                R.drawable.ic_star_empty
                            }
                        )
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    private fun bindViews() {

    }
}