package com.yb.part5_chapter05.presentation.stations

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.yb.part5_chapter05.databinding.FragmentStationsBinding
import com.yb.part5_chapter05.domain.Station
import com.yb.part5_chapter05.extension.toGone
import com.yb.part5_chapter05.extension.toVisible
import org.koin.android.scope.ScopeFragment

class StationsFragment : ScopeFragment(), StationsContract.View {

    override val presenter: StationsContract.Presenter by inject()

    private var binding: FragmentStationsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStationsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
        presenter.onDestroyView()
    }

    override fun showLoadingIndicator() {
        binding?.progressBar?.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.toGone()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showStations(stations: List<Station>) {
        (binding?.recyclerView?.adapter as StationsAdapter).run {
            this.data = stations
            notifyDataSetChanged()
        }
    }

    private fun initViews() {
        binding?.recyclerView?.apply {
            adapter = StationsAdapter()
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun bindViews() {
        binding?.searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterStations(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        (binding?.recyclerView?.adapter as StationsAdapter).apply {
            onItemClickListener = { station ->
                val action = StationsFragmentDirections.toStationArrivalsAction(station)
                findNavController().navigate(action)
            }
            onFavoriteClickListener = { station ->
            }
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

}