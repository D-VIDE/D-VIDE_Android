package com.d_vide.D_VIDE.app.data.remote

import com.d_vide.D_VIDE.app.data.remote.dto.Recruiting
import retrofit2.http.GET

interface RecruitingsApi {

    @GET("/v1/nearByPosts")
    suspend fun getRecruitings(): List<Recruiting>
}