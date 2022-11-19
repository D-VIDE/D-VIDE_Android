package com.d_vide.D_VIDE.app.presentation.view.PostRecruiting

import android.net.Uri
import com.d_vide.D_VIDE.app._enums.Category

sealed class PostRecruitingsEvent {
    data class EnteredTitle(val value: String): PostRecruitingsEvent()
    data class EnteredCategory(val value: Category): PostRecruitingsEvent()
    data class EnteredStoreName(val value: String): PostRecruitingsEvent()
    data class EnteredDeliveryPrice(val value: Int?): PostRecruitingsEvent()
    data class EnteredTargetPrice(val value: Int?): PostRecruitingsEvent()
    data class EnteredTargetTime(val value: Long): PostRecruitingsEvent()
    data class EnteredImage(val value: Uri?, val index: Int): PostRecruitingsEvent()
    data class DeleteImage(val index: Int): PostRecruitingsEvent()
    data class EnteredLocation(val latitude: Double, val longitude: Double): PostRecruitingsEvent()
    data class EnteredContent(val value: String): PostRecruitingsEvent()

    object SaveRecruiting: PostRecruitingsEvent()
}