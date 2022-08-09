package com.d_vide.D_VIDE.app.data.remote.dto

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class RecruitingsDTO (
    @SerializedName("data")
    var recruitings: List<Recruiting>
)