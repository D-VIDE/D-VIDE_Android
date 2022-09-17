package com.d_vide.D_VIDE.app.data.remote.responseDTO.Review

import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserInPostDTO

data class ReviewDetailDataDTO(
    val user: UserInPostDTO,
    val reviewDetail: ReviewPostDetailDTO
)
