package com.d_vide.D_VIDE.app.presentation.Recruitings

import com.d_vide.D_VIDE.app.data.remote.dto.Recruiting
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingsDTO

data class RecruitingsState(
    val isLoading: Boolean = false,
    val recruitings: List<Recruiting> = emptyList(),
    val error: String = ""
)
