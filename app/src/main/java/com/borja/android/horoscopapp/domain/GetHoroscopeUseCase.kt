package com.borja.android.horoscopapp.domain

import com.borja.android.horoscopapp.core.network.ResultType
import com.borja.android.horoscopapp.data.network.HoroscopeRepository
import com.borja.android.horoscopapp.domain.dto.HoroscopeDTO
import com.borja.android.horoscopapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val horoscopeRepository: HoroscopeRepository) {

    operator fun invoke(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> =
        horoscopeRepository.getHoroscope(horoscopeDTO)

}