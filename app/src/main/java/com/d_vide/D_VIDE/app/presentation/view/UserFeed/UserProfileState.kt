package com.d_vide.D_VIDE.app.presentation.view.UserFeed

import com.d_vide.D_VIDE.app.data.remote.responseDTO.OtherUserInfoDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.UserlessReviewsDTO

data class UserProfileState (
    val isLoading: Boolean = false,
    val userProfile: OtherUserInfoDTO = OtherUserInfoDTO(),
    val error: String = ""
)

data class UserReviewsState (
    val isLoading: Boolean = false,
    val userReviews: UserlessReviewsDTO = UserlessReviewsDTO(emptyList()),
    val error: String = ""
)