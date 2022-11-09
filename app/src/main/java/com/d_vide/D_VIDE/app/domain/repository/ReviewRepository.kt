package com.d_vide.D_VIDE.app.domain.repository

import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.*
import retrofit2.Response

interface ReviewRepository {
    suspend fun getReviews(
        longitude: Double,
        latitude: Double,
        first: Int
    ): Response<ReviewsDTO>

    suspend fun postLike( reviewId: Long): Response<ReviewLikeDTO>
    suspend fun postUnlike( reviewId: Long): Response<ReviewLikeDTO>
    suspend fun getReviewDetail( reviewId: Long): Response<ReviewDetailDTO>
    suspend fun getRecommend(): Response<RecommendStores>
    suspend fun getStoreReview( storeName: String): Response<StoreReviewsDTO>
    suspend fun getMyReviews(first: Int): Response<ReviewsDTO>
    suspend fun getMyOtherReviews(first: Int, userId: Long): Response<UserlessReviewsDTO>
}