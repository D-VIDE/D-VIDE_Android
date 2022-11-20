package com.d_vide.D_VIDE.app.data.remote.responseDTO.Review

data class ReviewInPostDTO(
    val reviewId: Long,
    val longitude: Double,
    val latitude: Double,
    val content: String,
    val starRating: Double,
    val reviewImgUrl: String,
    val storeName: String,
    val likeCount: Integer,
    var isLiked: Boolean
)
