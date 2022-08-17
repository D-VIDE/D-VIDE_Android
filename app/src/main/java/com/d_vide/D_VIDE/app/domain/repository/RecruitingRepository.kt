package com.d_vide.D_VIDE.app.domain.repository

import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingBodyDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingIdDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingOrderDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.ReviewBodyDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingOrderIdDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingsDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.ReviewIdDTO
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
    suspend fun postRecruitingOrder(recruitingOrder: RecruitingOrderDTO, files: List<File>): Response<RecruitingOrderIdDTO>
    suspend fun postReview(reviewBody: ReviewBodyDTO, files: List<File>, postId: Int): Response<ReviewIdDTO>
}