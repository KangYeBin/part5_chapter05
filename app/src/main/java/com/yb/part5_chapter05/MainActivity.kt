package com.yb.part5_chapter05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yb.part5_chapter05.R
import com.yb.part5_chapter05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}