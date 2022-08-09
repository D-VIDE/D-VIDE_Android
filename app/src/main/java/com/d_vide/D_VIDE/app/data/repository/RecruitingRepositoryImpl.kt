package com.d_vide.D_VIDE.app.data.repository

import android.util.Log
import com.d_vide.D_VIDE.app.data.remote.RecruitingsApi
import com.d_vide.D_VIDE.app.data.remote.dto.Recruiting
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingsDTO
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import retrofit2.Response
import javax.inject.Inject

class RecruitingRepositoryImpl @Inject constructor(
    private val api: RecruitingsApi
) : RecruitingRepository {

    override suspend fun getRecruitings(latitude: Double, longitude: Double): Response<RecruitingsDTO> {
        return api.getRecruitings(latitude, longitude)
    }
}