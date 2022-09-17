package com.d_vide.D_VIDE.app.presentation.Reviews

import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.RecommendStore
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.ReviewDTO

data class ReviewsState(
    val isLoading: Boolean = false,
    val reviews: List<ReviewDTO> = emptyList(),
    val error: String = "",
    val recommendStore: List<RecommendStore> = emptyList()
)
