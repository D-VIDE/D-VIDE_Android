package com.d_vide.D_VIDE.app.data.remote.dto

import com.d_vide.D_VIDE.app._enums.Category
import java.time.LocalDateTime

data class RecruitingBodyDTO(
    val category: String? = Category.KOREAN_FOOD.value,
    val content: String? = null,
    val deliveryPrice: Int? = null,
    val latitude: Double? = 37.49015482509,
    val longitude: Double? = 127.030767490,
    val storeName: String? = null,
    val targetPrice: Int? = null,
    val targetTime: String? = "2022-08-30 15:12:35",
    val title: String? = null
)