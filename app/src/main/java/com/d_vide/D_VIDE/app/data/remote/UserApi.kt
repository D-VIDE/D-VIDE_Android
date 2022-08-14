package com.d_vide.D_VIDE.app.data.remote

import com.d_vide.D_VIDE.app.data.remote.dto.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.dto.UserDTO
import com.d_vide.D_VIDE.app.domain.model.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body emailPw: EmailPasswordDTO
    ): Response<Token>

    @GET("/api/v1/user")
    suspend fun getUserInfo(): Response<UserDTO>
}