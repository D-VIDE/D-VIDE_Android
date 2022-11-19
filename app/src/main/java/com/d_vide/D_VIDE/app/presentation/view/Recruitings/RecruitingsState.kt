package com.d_vide.D_VIDE.app.presentation.view.Recruitings

import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingDTO

data class RecruitingsState(
    val isLoading: Boolean = false,
    val recruitingDTOS: List<RecruitingDTO> = emptyList(),
    val error: String = ""
)
