package com.d_vide.D_VIDE.app.presentation.view.MyReviews


import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.ReviewDTO

data class MyReviewsState(
    val isLoading: Boolean = false,
    val reviews: List<ReviewDTO> = emptyList(),
    val error: String = "",
    val offset: Int = 1,
    val endReached: Boolean = false,
    val pagingLoading : Boolean = false,
)