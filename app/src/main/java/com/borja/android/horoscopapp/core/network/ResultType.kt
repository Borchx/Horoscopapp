package com.borja.android.horoscopapp.core.network

//T -> va a recibir un valor de cualquier tipo
sealed class ResultType<T> {

    //val example = ResultType.Success<HoroscopeResponse>()

    data class Success<T>(val data: T?) : ResultType<T>()

    //val exampleError = ResultType.Error<HoroscopeResponse>("ERROR en la APP")
    data class Error<T>(val errorType: ErrorType) : ResultType<T>()
}