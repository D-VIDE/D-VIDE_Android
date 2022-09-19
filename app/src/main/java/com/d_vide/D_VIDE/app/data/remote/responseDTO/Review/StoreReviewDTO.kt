package com.d_vide.D_VIDE.app.data.remote.responseDTO.Review

data class StoreReviewDTO(
      val reviewId: Long,
      val profileImgUrl: String,
      val nickname: String,
      val longitude: Double,
      val latitude: Double,
      val content: String,
      val starRating: Double,
      val reviewImgUrl: String,
      val likeCount: Int,
      val hasLike: Boolean,
      val storeName : String
)
