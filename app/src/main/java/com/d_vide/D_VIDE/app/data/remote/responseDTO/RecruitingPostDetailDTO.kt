package com.d_vide.D_VIDE.app.data.remote.responseDTO

/**
 * 모집 글 내부에서 유저 정보를 제외한 상세 요소
 */
data class RecruitingPostDetailDTO(
    val id: Int,
    val longitude: Double,
    val latitude: Double,
    val title: String,
    val targetTime: Long,
    val targetPrice: Int,
    val deliveryPrice: Int,
    val orderedPrice: Int,
    val content: String,
    val storeName: String,
    val postImgUrl: List<String>,
)
