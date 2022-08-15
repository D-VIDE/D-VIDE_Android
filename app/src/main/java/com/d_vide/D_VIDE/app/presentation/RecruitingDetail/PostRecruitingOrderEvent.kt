package com.d_vide.D_VIDE.app.presentation.RecruitingDetail



sealed class PostRecruitingOrderEvent{
    data class EnteredPostId(val value: Long?): PostRecruitingOrderEvent()
    data class EnteredOrderPrice(val value: Int?): PostRecruitingOrderEvent()
    object SaveRecruitingOrder: PostRecruitingOrderEvent()
}