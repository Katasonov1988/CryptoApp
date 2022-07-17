package com.example.cryptoapp.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import java.security.Timestamp
import java.util.*

// преобразование секунд с 1970 года в нужный формат
fun convertTimestampToTime(timestamp: Long?): String {
    if (timestamp == null) return ""
    val stamp = java.sql.Timestamp(timestamp * 1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)

}