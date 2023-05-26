package com.borja.android.horoscopapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borja.android.horoscopapp.core.network.ResultType
import com.borja.android.horoscopapp.data.network.model.HoroscopeResponse
import com.borja.android.horoscopapp.domain.GetHoroscopeUseCase
import com.borja.android.horoscopapp.domain.dto.HoroscopeDTO
import com.borja.android.horoscopapp.domain.model.HoroscopeModel
import com.borja.android.horoscopapp.ui.detail.model.DetailUIState
import com.borja.android.horoscopapp.ui.detail.model.DetailUIState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<DetailUIState>(DetailUIState.Loading)
    val uiState: StateFlow<DetailUIState> = _uiState
    fun getHoroscope() {
        viewModelScope.launch {
            getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))
                .collect { result: ResultType<HoroscopeModel> ->
                    when (result) {
                        is ResultType.Error -> {
                            _uiState.value = DetailUIState.Error(result.msg ?: "Error ni idea")
                        }
                        is ResultType.Success -> {
                            _uiState.value = Success(result.data!!)
                        }
                    }

                }
        }
    }
}