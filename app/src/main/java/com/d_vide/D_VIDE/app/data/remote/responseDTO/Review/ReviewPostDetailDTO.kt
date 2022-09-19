package com.d_vide.D_VIDE.app.data.remote.responseDTO.Review

data class ReviewPostDetailDTO(
    val reviewId: Long = 0,
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val content: String = "",
    val starRating: Double = 0.0,
    val reviewImgUrl: List<String> = emptyList(),
    val storeName: String = "",
    val likeCount: Int = 0,
    val liked: Boolean = false
)
