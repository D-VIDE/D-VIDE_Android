package com.d_vide.D_VIDE.app.data.repository

import com.d_vide.D_VIDE.app.data.remote.RecruitingsApi
import com.d_vide.D_VIDE.app.data.remote.ReviewsApi
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.*
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import com.d_vide.D_VIDE.app.domain.repository.ReviewRepository
import retrofit2.Response
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val api: ReviewsApi
) : ReviewRepository {

    override suspend fun getReviews(
        longitude: Double,
        latitude: Double,
        first: Int
    ): Response<ReviewsDTO> {
        return api.getReviews(longitude, latitude, first)
    }

    override suspend fun postLike(reviewId: Long): Response<ReviewLikeDTO> {
        return api.postLike(reviewId)
    }

    override suspend fun postUnlike(reviewId: Long): Response<ReviewLikeDTO> {
        return api.postUnlike(reviewId)
    }

    override suspend fun getReviewDetail(reviewId: Long): Response<ReviewDetailDTO> {
        return api.getReviewDetail(reviewId)
    }

    override suspend fun getRecommend(): Response<RecommendStores> {
        return api.getRecommend()
    }

    override suspend fun getStoreReview(storeName: String): Response<StoreReviewsDTO> {
        return api.getStoreReviews(storeName)
    }

    override suspend fun getMyReviews(first: Int): Response<ReviewsDTO> {
        return api.getMyReviews(first)
    }
}