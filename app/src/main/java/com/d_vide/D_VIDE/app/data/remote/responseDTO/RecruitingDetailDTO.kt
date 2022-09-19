package com.d_vide.D_VIDE.app.data.remote.responseDTO

import com.google.gson.annotations.SerializedName


/**
 * 상세 게시글(모집) 조회 API DTO
 * data 로 한번 더 묶여있음
 */
data class RecruitingDetailDTO(
    @SerializedName("data")
    val recruitingDetail : RecruitingDetailDataDTO
)
