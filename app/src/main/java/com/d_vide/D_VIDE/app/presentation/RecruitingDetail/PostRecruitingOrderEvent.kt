package com.d_vide.D_VIDE.app.presentation.RecruitingDetail

import android.net.Uri


sealed class PostRecruitingOrderEvent{
    data class EnteredPostId(val value: Long?): PostRecruitingOrderEvent()
    data class EnteredOrderPrice(val value: Int?): PostRecruitingOrderEvent()
    data class EnteredImage(val value: Uri?, val index: Int): PostRecruitingOrderEvent()
    data class DeleteImage(val index: Int): PostRecruitingOrderEvent()
    object SaveRecruitingOrder: PostRecruitingOrderEvent()
}