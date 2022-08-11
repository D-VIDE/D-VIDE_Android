package com.d_vide.D_VIDE.app.data.repository

import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.data.remote.RecruitingsApi
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingBodyDTO
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingIdDTO
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingsDTO
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import retrofit2.Response
import javax.inject.Inject

class RecruitingRepositoryImpl @Inject constructor(
    private val api: RecruitingsApi
) : RecruitingRepository {

    override suspend fun getRecruitings(
        latitude: Double,
        longitude: Double,
        category: Category,
        offset: Int
    ): Response<RecruitingsDTO> {
        return api.getRecruitings(latitude, longitude, category.tag, offset)
    }

    override suspend fun postRecruiting(userId: Int, recruitingBody: RecruitingBodyDTO): Response<RecruitingIdDTO> {
        return api.postRecruiting(userId, recruitingBody)
    }
}