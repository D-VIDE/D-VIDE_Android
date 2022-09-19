package com.d_vide.D_VIDE.app.data.remote.responseDTO

/**
 * 상세 게시글(모집) 조회 API DTO
 */
data class RecruitingDetailDataDTO(
    val user : UserInPostDTO = UserInPostDTO(),
    val postDetail : RecruitingPostDetailDTO = RecruitingPostDetailDTO()
)
