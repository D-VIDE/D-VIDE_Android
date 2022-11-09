package com.d_vide.D_VIDE.app.presentation.UserFeed

import com.d_vide.D_VIDE.app.data.remote.responseDTO.OtherUserInfoDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.ReviewsDTO

data class UserProfileState (
    val isLoading: Boolean = false,
    val userProfile: OtherUserInfoDTO = OtherUserInfoDTO(),
    val error: String = ""
)

data class UserReviewsState (
    val isLoading: Boolean = false,
    val userReviews: ReviewsDTO = ReviewsDTO(emptyList()),
    val error: String = ""
)