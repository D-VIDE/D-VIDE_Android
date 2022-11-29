package com.d_vide.D_VIDE.app.presentation.view.Reviews

import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.RecommendStore
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.ReviewDTO

data class ReviewsState(
    val isLoading: Boolean = false,
    var reviews: List<ReviewDTO> = emptyList(),
    val error: String = "",
    val recommendStore: List<RecommendStore> = emptyList(),
    val offset: Int = 1,
    val endReached: Boolean = false,
    val pagingLoading : Boolean = false,
)
