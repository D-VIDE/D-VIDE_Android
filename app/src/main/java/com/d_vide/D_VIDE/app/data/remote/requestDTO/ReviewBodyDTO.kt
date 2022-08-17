package com.d_vide.D_VIDE.app.data.remote.requestDTO

import com.d_vide.D_VIDE.app._enums.Category

data class ReviewBodyDTO(
    val starRating: Double = 5.0,
    val storeName: String? = null,
    val content: String? = null
)
