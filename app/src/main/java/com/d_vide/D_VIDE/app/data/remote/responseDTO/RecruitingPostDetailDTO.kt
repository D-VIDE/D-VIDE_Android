package com.d_vide.D_VIDE.app.data.remote.responseDTO

/**
 * 모집 글 내부에서 유저 정보를 제외한 상세 요소
 */
data class RecruitingPostDetailDTO(
    val id: Int = 0,
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val title: String = "",
    val targetTime: Long = 0,
    val targetPrice: Int = 1,
    val deliveryPrice: Int = 0,
    val orderedPrice: Int = 1,
    val content: String = "",
    val storeName: String = "",
    val postImgUrls: List<String> = emptyList(),
)
