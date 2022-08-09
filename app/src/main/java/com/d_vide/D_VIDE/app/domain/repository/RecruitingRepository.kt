package com.d_vide.D_VIDE.app.domain.repository

import com.d_vide.D_VIDE.app.data.remote.dto.Recruiting
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingsDTO
import retrofit2.Response

interface RecruitingRepository {

    suspend fun getRecruitings(latitude: Double, longitude: Double): Response<RecruitingsDTO>
}