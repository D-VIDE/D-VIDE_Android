package com.d_vide.D_VIDE.app.presentation.RecruitingDetail

import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingDetailDataDTO

/**
 * recruitingDetailScreen의 State
 */
data class recruitingDetailState(
    val isLoading: Boolean = false,
    val recruitingDetail: RecruitingDetailDataDTO = RecruitingDetailDataDTO(),
    val error: String = ""
)
