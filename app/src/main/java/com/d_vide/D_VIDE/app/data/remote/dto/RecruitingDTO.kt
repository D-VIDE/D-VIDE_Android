package com.d_vide.D_VIDE.app.data.remote.dto

data class RecruitingDTO(
    val category: String,
    val latitude: Double,
    val longitude: Double,
    val nickname: String,
    val postId: Int,
    val profileImgUrl: String,
    val targetPrice: Int,
    val targetTime: List<Int>,
    val title: String
)