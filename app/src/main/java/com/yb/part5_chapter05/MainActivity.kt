package com.yb.part5_chapter05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.yb.part5_chapter05.R
import com.yb.part5_chapter05.databinding.ActivityMainBinding
import com.yb.part5_chapter05.extension.toGone
import com.yb.part5_chapter05.extension.toVisible
import com.yb.part5_chapter05.presentation.stationarrivals.StationArrivalsFragmentArgs

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.mainNavigationHostContainer) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        bindViews()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navigationController)
    }

    private fun bindViews() {
        navigationController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.station_arrivals_dest) {
                title = StationArrivalsFragmentArgs.fromBundle(arguments!!).station.stationName
                binding.toolbar.toVisible()
            } else {
                binding.toolbar.toGone()
            }
        }
    }
}