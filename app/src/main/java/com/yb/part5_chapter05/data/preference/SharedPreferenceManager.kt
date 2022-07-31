package com.yb.part5_chapter05.data.preference

import android.annotation.SuppressLint
import android.content.SharedPreferences

class SharedPreferenceManager(private val sharedPreferences: SharedPreferences) :
    PreferenceManager {
    override fun getLong(key: String): Long? {
        val value = sharedPreferences.getLong(key, INVALID_LONG_NAME)

        return if (value == INVALID_LONG_NAME) {
            null
        } else {
            value
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun putLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value)
    }

    companion object {
        private const val INVALID_LONG_NAME = Long.MIN_VALUE
    }
}