package com.d_vide.D_VIDE.app.data.remote.responseDTO.Review

import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserInPostDTO

data class ReviewDTO(
    val user: UserInPostDTO,
    val review: ReviewInPostDTO
)
