package com.d_vide.D_VIDE.app.data.remote.responseDTO

import java.sql.Timestamp

data class RecruitingDTO(
    val postId: Int,
    val profileImgUrl: String,
    val nickname: String,
    val longitude: Double,
    val latitude: Double,
    val targetTime: Long,
    val title: String,
    val targetPrice: Int,
    val category: String
)