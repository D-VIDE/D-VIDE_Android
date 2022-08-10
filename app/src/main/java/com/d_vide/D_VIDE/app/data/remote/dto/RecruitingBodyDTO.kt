package com.d_vide.D_VIDE.app.data.remote.dto

import java.time.LocalDateTime

data class RecruitingBodyDTO(
    val category: String? = null,
    val content: String? = null,
    val deliveryPrice: Int? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val postStatus: String? = null,
    val storeName: String? = null,
    val targetPrice: Int? = null,
    val targetTime: LocalDateTime? = null,
    val title: String? = null
)