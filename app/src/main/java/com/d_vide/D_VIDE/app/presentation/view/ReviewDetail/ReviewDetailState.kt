package com.d_vide.D_VIDE.app.presentation.view.ReviewDetail

import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingDetailDataDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.RecommendStore
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.ReviewDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.ReviewDetailDataDTO

data class ReviewDetailState(
    val isLoading: Boolean = false,
    val reviewDetail: ReviewDetailDataDTO = ReviewDetailDataDTO(),
    val error: String = ""
)
