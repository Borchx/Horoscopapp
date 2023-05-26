package com.borja.android.horoscopapp.ui.detail.model

import com.borja.android.horoscopapp.data.network.model.HoroscopeResponse

sealed class DetailUIState {
    object Loading : DetailUIState()
    data class Success(val horoscopeResponse: HoroscopeResponse): DetailUIState()
    data class Error(val msg: String): DetailUIState()
}