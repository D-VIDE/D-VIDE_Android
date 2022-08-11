package com.d_vide.D_VIDE.app.data.remote.dto

import com.d_vide.D_VIDE.app._enums.Category
import java.time.LocalDateTime

data class RecruitingBodyDTO(
    val category: String? = Category.ALL.tag,
    val content: String? = null,
    val deliveryPrice: Int? = null,
    val latitude: Double? = 37.49015482509,
    val longitude: Double? = 127.030767490,
    val storeName: String? = null,
    val targetPrice: Int? = null,
    val targetTime: Long? = (System.currentTimeMillis()/1000 + 60*30),
    val title: String? = null
)