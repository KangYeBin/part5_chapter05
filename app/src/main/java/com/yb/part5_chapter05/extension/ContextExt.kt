package com.yb.part5_chapter05.extension

import android.content.Context

fun Context.dip(dipValue: Float) = (dipValue * resources.displayMetrics.density).toInt()