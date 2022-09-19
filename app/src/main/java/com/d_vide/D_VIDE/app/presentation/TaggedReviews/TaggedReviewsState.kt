package com.d_vide.D_VIDE.app.presentation.TaggedReviews

import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.StoreReviewDTO

data class TaggedReviewsState(
    val isLoading: Boolean = false,
    val reviews: List<StoreReviewDTO> = emptyList(),
    val error: String = ""
)
