package com.d_vide.D_VIDE.app.data.remote.requestDTO

/**
 * @param orderPrice 주문 금액
 * @param postId 모집글의 아이디
 */
data class RecruitingOrderDTO(
    val orderPrice: Int? = null,
    val postId: Long? = null
)
