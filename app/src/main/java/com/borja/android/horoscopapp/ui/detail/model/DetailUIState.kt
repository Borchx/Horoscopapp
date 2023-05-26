package com.borja.android.horoscopapp.ui.detail.model

import com.borja.android.horoscopapp.domain.model.HoroscopeModel

sealed class DetailUIState {
    object Loading : DetailUIState()
    data class Success(val horoscopeModel: HoroscopeModel): DetailUIState()
    data class Error(val msg: String): DetailUIState()
}