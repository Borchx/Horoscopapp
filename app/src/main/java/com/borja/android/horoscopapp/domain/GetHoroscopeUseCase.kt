package com.borja.android.horoscopapp.domain

import com.borja.android.horoscopapp.core.network.ResultType
import com.borja.android.horoscopapp.data.HoroscopeRepository
import com.borja.android.horoscopapp.domain.dto.HoroscopeDTO
import com.borja.android.horoscopapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val horoscopeRepository: HoroscopeRepository) {

    //operator fun invoke(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> =
    //    horoscopeRepository.getHoroscope(horoscopeDTO)

    suspend operator fun invoke(horoscopeDTO: HoroscopeDTO): ResultType<HoroscopeModel> =
        horoscopeRepository.getHoroscope(horoscopeDTO)

}