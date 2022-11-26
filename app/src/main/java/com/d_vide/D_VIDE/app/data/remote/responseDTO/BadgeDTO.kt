package com.d_vide.D_VIDE.app.data.remote.responseDTO

import kotlinx.serialization.Serializable

@Serializable
data class BadgeDTO(
    val name: String = "",
    val description: String = ""
)
