package com.d_vide.D_VIDE.app.presentation.view.TaggedReviews

import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.ReviewDTO

data class TaggedReviewsState(
    val isLoading: Boolean = false,
    val reviews: List<ReviewDTO> = emptyList(),
    val error: String = ""
)
