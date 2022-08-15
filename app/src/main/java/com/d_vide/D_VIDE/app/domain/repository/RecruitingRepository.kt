package com.d_vide.D_VIDE.app.domain.repository

import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.data.remote.dto.*
import retrofit2.Response
import java.io.File

interface RecruitingRepository {

    suspend fun getRecruitings(
        latitude: Double,
        longitude: Double,
        category: Category,
        offset: Int
    ): Response<RecruitingsDTO>

    suspend fun postRecruiting(recruitingBody: RecruitingBodyDTO, files: List<File>): Response<RecruitingIdDTO>
    
    suspend fun postRecruitingOrder(recruitingOrder: RecruitingOrderDTO): Response<RecruitingOrderIdDTO>
}