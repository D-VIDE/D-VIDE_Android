package com.d_vide.D_VIDE.app.data.remote.dto

import java.time.LocalDateTime

data class RecruitingBodyDTO(
    val category: String,
    val content: String,
    val deliveryPrice: Int,
    val latitude: Double,
    val longitude: Double,
    val postStatus: String,
    val storeName: String,
    val targetPrice: Int,
    val targetTime: LocalDateTime,
    val title: String
)