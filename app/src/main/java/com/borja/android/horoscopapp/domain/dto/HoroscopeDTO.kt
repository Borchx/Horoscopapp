package com.borja.android.horoscopapp.domain.dto

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class HoroscopeDTO(
    val sign: String,
    val date: String = getCurrentDat(),
    val lang: String = "es"
) {

}

fun getCurrentDat(): String {
    // val currentDay = LocalDate.now() - version minsdk >= 26
    val currentDay = Date()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    return dateFormat.format(currentDay)
}
