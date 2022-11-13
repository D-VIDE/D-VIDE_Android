package com.d_vide.D_VIDE.app.data.repository

import com.d_vide.D_VIDE.app.data.remote.ReviewsApi
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.*
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

    override suspend fun getStoreReview(first: Int, storeName: String): Response<ReviewsDTO> {
        return api.getStoreReviews(first, storeName)
    }

    override suspend fun getMyReviews(first: Int): Response<ReviewsDTO> {
        return api.getMyReviews(first)
    }

    override suspend fun getMyOtherReviews(first: Int, userId: Long): Response<UserlessReviewsDTO> {
        return api.getOtherReviews(first, userId)
    }
}