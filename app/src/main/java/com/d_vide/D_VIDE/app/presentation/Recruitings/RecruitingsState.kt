package com.d_vide.D_VIDE.app.presentation.Recruitings

import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingDTO

data class RecruitingsState(
    val isLoading: Boolean = false,
    val recruitingDTOS: List<RecruitingDTO> = emptyList(),
    val error: String = ""
)
