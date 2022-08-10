package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import com.d_vide.D_VIDE.app._enums.Category

sealed class PostRecruitingsEvent {
    data class EnteredTitle(val value: String): PostRecruitingsEvent()
    data class EnteredCategory(val value: Category): PostRecruitingsEvent()
    data class EnteredStoreName(val value: String): PostRecruitingsEvent()
    data class EnteredDeliveryPrice(val value: Int): PostRecruitingsEvent()
    data class EnteredTargetPrice(val value: Int): PostRecruitingsEvent()
    data class EnteredTargetTime(val value: String): PostRecruitingsEvent()
    data class EnteredImage(val value: String): PostRecruitingsEvent()
    data class EnteredLocation(val latitude: Double, val longitude: Double): PostRecruitingsEvent()
    data class EnteredContent(val value: String): PostRecruitingsEvent()

    object SaveRecruiting: PostRecruitingsEvent()
}