package com.d_vide.D_VIDE.app.presentation.Recruitings

import com.d_vide.D_VIDE.app.data.remote.dto.Recruiting

data class RecruitingsState(
    val isLoading: Boolean = false,
    val coins: List<Recruiting> = emptyList(),
    val error: String = ""
)
