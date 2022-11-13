package com.d_vide.D_VIDE.app.data.remote.responseDTO.Review

import com.google.gson.annotations.SerializedName

data class UserlessReviewsDTO(
    @SerializedName("data")
    val reviews: List<ReviewInPostDTO>
)
