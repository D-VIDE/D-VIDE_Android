package com.d_vide.D_VIDE.app.data.remote.responseDTO.Review

import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserInPostDTO

data class ReviewDTO(
    val user: UserInPostDTO,
    var review: ReviewInPostDTO
)
