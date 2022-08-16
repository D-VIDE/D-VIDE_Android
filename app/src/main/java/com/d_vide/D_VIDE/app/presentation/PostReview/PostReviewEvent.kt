package com.d_vide.D_VIDE.app.presentation.PostReview

import android.net.Uri


sealed class PostReviewEvent {
    data class EnteredStoreName(val value: String): PostReviewEvent()
    data class EnteredImage(val value: Uri?, val index: Int): PostReviewEvent()
    data class DeleteImage(val index: Int): PostReviewEvent()
    data class EnteredContent(val value: String): PostReviewEvent()

    object SaveReview: PostReviewEvent()
}
