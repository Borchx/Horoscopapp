package com.borja.android.horoscopapp.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borja.android.horoscopapp.core.network.ResultType
import com.borja.android.horoscopapp.data.network.model.HoroscopeResponse
import com.borja.android.horoscopapp.domain.GetHoroscopeUseCase
import com.borja.android.horoscopapp.domain.dto.HoroscopeDTO
import com.borja.android.horoscopapp.domain.model.HoroscopeModel
import com.borja.android.horoscopapp.ui.detail.model.DetailUIState
import com.borja.android.horoscopapp.ui.detail.model.DetailUIState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<DetailUIState>(DetailUIState.Loading)
    val uiState: StateFlow<DetailUIState> = _uiState

    //corrutina
    //MAIN = UI
    //IO = ROOM, Retrofit, etc
    //Default = Procesos largos
    fun getHoroscope() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))) {
                is ResultType.Error -> {
                    Log.i("BorchxHoroscope", "Fail")
                    _uiState.value = Error("error")
                }

                is ResultType.Success -> {
                    Log.i("BorchxHoroscope", "Dentro")
                    if (response.data != null) {
                        _uiState.value = Success(response.data)
                    }
                }
            }
            /*withContext(Dispatchers.Default){

            }*/
            val response1 = async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries")) }
            val response2 = async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries")) }
            val response3 = async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries")) }
            //response1.await()
            //response2.await()
            //response3.wait()
/*            val deferreds = listOf(
                async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))},
                async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))},
                async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))},
                async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))},
                async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))},
                async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))},
                async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))},
                async { getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))}
            )
            val a:List<ResultType<HoroscopeModel>> = deferreds.awaitAll()*/
        }
        //myJob.cancel()
    }


    //flow
    /*    fun getHoroscope() {
            viewModelScope.launch {
                getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))
                    .collect { result: ResultType<HoroscopeModel> ->
                        when (result) {
                            is ResultType.Error -> {
                                *//*val error = when (result.errorType) {
                                ErrorType.BadRequest -> "Mensaje generico"
                                is ErrorType.ExceptionError -> "Mensaje generico"
                                ErrorType.Forbidden -> "Mensaje generico"
                                ErrorType.InternalServerError -> "Mensaje generico"
                                ErrorType.InvalidData -> "Mensaje generico"
                                is ErrorType.UncontrolledError -> "Mensaje generico"
                            }*//*
                            _uiState.value = Error("Error")
                        }

                        is ResultType.Success -> {
                            _uiState.value = Success(result.data!!)
                        }
                    }

                }
        }
    }*/
}