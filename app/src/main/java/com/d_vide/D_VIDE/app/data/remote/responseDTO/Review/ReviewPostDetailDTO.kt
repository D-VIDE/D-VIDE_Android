package com.d_vide.D_VIDE.app.data.remote.responseDTO.Review

data class ReviewPostDetailDTO(
    val reviewId: Long,
    val longitude: Double,
    val latitude: Double,
    val content: String,
    val starRating: Double,
    val reviewImgUrl: List<String>,
    val storeName: String,
    val likeCount: Integer,
    val liked: Boolean
)
