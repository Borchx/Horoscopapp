package com.borja.android.horoscopapp.data

import android.util.Log
import com.borja.android.horoscopapp.core.network.ErrorType
import com.borja.android.horoscopapp.core.network.ResultType
import com.borja.android.horoscopapp.data.network.HoroscopeApi
import com.borja.android.horoscopapp.data.network.model.toDomain
import com.borja.android.horoscopapp.domain.dto.HoroscopeDTO
import com.borja.android.horoscopapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: HoroscopeApi) : BaseRepository() {

    //corrutina basica
    suspend fun getHoroscope(horoscopeDTO: HoroscopeDTO): ResultType<HoroscopeModel> {
        return try {
            val response = api.getHoroscope(horoscopeDTO.sign, horoscopeDTO.date, horoscopeDTO.lang)
        if(response.isSuccessful){
            response.body()?.let { horoscopeResponse ->
                ResultType.Success(horoscopeResponse.toDomain())
            } ?: ResultType.Error(ErrorType.UncontrolledError(999))
        }else{
            val error = when(response.code()){
                ErrorType.BadRequest.errorCode -> ErrorType.BadRequest
                ErrorType.InvalidData.errorCode -> ErrorType.InvalidData
                ErrorType.Forbidden.errorCode -> ErrorType.Forbidden
                ErrorType.InternalServerError.errorCode -> ErrorType.InternalServerError
                else -> ErrorType.UncontrolledError(response.code())
            }
            Log.i("BorchxHoroscope" , "Error")
            ResultType.Error(error)
        }
        }catch (e: Exception){
            Log.i("BorchxHoroscope" , "Exception")
            ResultType.Error(ErrorType.ExceptionError(e))
        }

    }


    // flow avanzado
    /*
        fun getHoroscope(horoscopeDTO: HoroscopeDTO) =
            runApiCallFlow { api.getHoroscope(horoscopeDTO.sign, horoscopeDTO.date, horoscopeDTO.lang) }

    */

    //flow basico
    /*fun getHoroscope(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> = flow {

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
    }*/

}