package com.d_vide.D_VIDE.app.data.remote.responseDTO

data class RecruitingPostInfoDTO(
    val id: Int,
    val longitude: Double,
    val latitude: Double,
    val title: String,
    val targetTime: Long,
    val targetPrice: Int,
    val orderedPrice: Int,
    val status: String,
    val postImgUrl: String,
)