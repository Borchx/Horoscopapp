package com.borja.android.horoscopapp.data.network

import com.borja.android.horoscopapp.core.network.ResultType
import com.borja.android.horoscopapp.data.network.model.HoroscopeResponse
import com.borja.android.horoscopapp.data.network.model.toDomain
import com.borja.android.horoscopapp.domain.dto.HoroscopeDTO
import com.borja.android.horoscopapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: HoroscopeApi) {

    fun getHoroscope(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> = flow {

        try {
            val response = api.getHoroscope(horoscopeDTO.sign, horoscopeDTO.date, horoscopeDTO.lang)
            if (response.isSuccessful) {
                response.body()?.let {horoscopeResponse ->
                    emit(ResultType.Success(horoscopeResponse.toDomain()))
                }

            } else {
                val msg = when (response.code()) {
                    404 -> "Not found"
                    405 -> "Error error"
                    else -> "Error genérico"
                }
                emit(ResultType.Error(msg))
            }
        } catch (e: Exception) {
            val msg = when (e) {
                is IOException -> "Error IO"
                else -> "Crash final "
            }
            emit(ResultType.Error(msg))
        }
    }

}