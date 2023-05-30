package com.borja.android.horoscopapp.data

import com.borja.android.horoscopapp.core.network.ErrorType
import com.borja.android.horoscopapp.core.network.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.Response

open class BaseRepository {

    inline fun <T> runApiCallFlow(crossinline call: () -> Response<T>): Flow<ResultType<T>> = flow {
        try {
            val response = call()
            if (response.isSuccessful) {
                emit(ResultType.Success(response.body()))
            } else {
                val error = when (response.code()) {
                    ErrorType.BadRequest.errorCode -> ErrorType.BadRequest
                    ErrorType.InvalidData.errorCode -> ErrorType.InvalidData
                    ErrorType.Forbidden.errorCode -> ErrorType.Forbidden
                    ErrorType.InternalServerError.errorCode -> ErrorType.InternalServerError
                    else -> ErrorType.UncontrolledError(response.code())
                }
                emit(ResultType.Error(error))
            }
        } catch (e: Exception) {
            emit(ResultType.Error(ErrorType.ExceptionError(e)))
        }
    }
}