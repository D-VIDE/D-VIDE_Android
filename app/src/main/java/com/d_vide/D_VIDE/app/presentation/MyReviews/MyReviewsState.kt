package com.d_vide.D_VIDE.app.presentation.MyReviews


import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.ReviewDTO

data class MyReviewsState(
    val isLoading: Boolean = false,
    val reviewsDTO: List<ReviewDTO> = emptyList(),
    val error: String = ""
)