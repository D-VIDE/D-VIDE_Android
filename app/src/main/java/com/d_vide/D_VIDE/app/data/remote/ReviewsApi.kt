package com.d_vide.D_VIDE.app.data.remote

import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingsDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.*
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewsApi {

    //리뷰 리스트 불러오기
    @GET("/api/v2/reviews")
    suspend fun getReviews(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @Query("first") first: Int
    ): Response<ReviewsDTO>

    //리뷰 좋아요 생성
    @GET("/api/v1/review/{reviewId}/like")
    suspend fun postLike(
        @Path("reviewId") reviewId: Double,
    ): Response<ReviewLikeDTO>

    //리뷰 좋아요 취소
    @DELETE("/api/v1/review/{reviewId}/like")
    suspend fun postUnlike(
        @Path("reviewId") reviewId: Double,
    ): Response<ReviewLikeDTO>

    //상세 리뷰글
    @GET("/api/v1/review/{reviewId}")
    suspend fun getReviewDetail(
        @Path("reviewId") reviewId: Double,
    ): Response<ReviewDetailDTO>

    //추천 맛집 조회
    @GET("/api/v1/reviews/recommend")
    suspend fun getRecommend(
    ): Response<RecommendStores>

    //식당 리뷰 검색시 조회
    @GET("/api/v1/reviews")
    suspend fun getStoreReviews(
        @Query("storeName") storeName: String
    ): Response<StoreReviewsDTO>

    //내가 쓴 리뷰 조회
    @GET("/api/v1/reviews/myself")
    suspend fun getMyReviews(
        @Query("first") first: Int
    ): Response<ReviewsDTO>
}