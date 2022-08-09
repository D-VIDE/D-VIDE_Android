package com.d_vide.D_VIDE.app.data.remote

import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecruitingsApi {

    // 근처 500m
    @GET("/api/v1/posts/nearby")
    suspend fun getRecruitings(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<RecruitingsDTO>
}