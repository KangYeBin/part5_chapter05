package com.yb.part5_chapter05.data.preference

interface PreferenceManager {

    fun getLong(key: String) : Long?

    fun putLong(key: String, value: Long)
}