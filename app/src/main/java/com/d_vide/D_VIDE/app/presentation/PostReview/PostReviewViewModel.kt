package com.d_vide.D_VIDE.app.presentation.PostReview

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingBodyDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.ReviewBodyDTO
import com.d_vide.D_VIDE.app.domain.use_case.PostRecruiting
import com.d_vide.D_VIDE.app.domain.use_case.PostReview
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.UriUtil
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.PostRecruitingViewModel
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.PostRecruitingsEvent
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostReviewViewModel @Inject constructor(
    val postReviewUseCase: PostReview,
    @ApplicationContext val context: Context
) : ViewModel() {
    private var _imageUris = mutableStateListOf<Uri>()
    val imageUris: SnapshotStateList<Uri> = _imageUris

    private var _reviewId = mutableStateOf(1L)
    val reviewId: State<Long> = _reviewId

    private var _reviewBody = mutableStateOf(ReviewBodyDTO())
    val reviewBodyDTO: State<ReviewBodyDTO> = _reviewBody

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    /**
     * starRating 추가하여 수정
     */
    fun onEvent(event: PostReviewEvent) {
        when (event) {
            is PostReviewEvent.EnteredStoreName -> {
                _reviewBody.value = reviewBodyDTO.value.copy(
                    storeName = event.value
                )
            }

            is PostReviewEvent.EnteredStarRating -> {
                _reviewBody.value = reviewBodyDTO.value.copy(
                    starRating = event.value
                )
            }

            is PostReviewEvent.EnteredImage -> {
                if (event.index >= 0)
                    _imageUris[event.index] = event.value!!
                else if (_imageUris.size < 3)
                    _imageUris.add(event.value!!)
            }
            is PostReviewEvent.DeleteImage -> {
                _imageUris.removeAt(event.index)
            }
            is PostReviewEvent.EnteredContent -> {
                _reviewBody.value = reviewBodyDTO.value.copy(
                    content = event.value
                )

            }
            is PostReviewEvent.SaveReview -> {
                viewModelScope.launch {
                    try {
                        // 모든 빈칸이 채워졌는지 check
                        if (reviewBodyDTO.value.content.isNullOrBlank() ||
                            reviewBodyDTO.value.storeName.isNullOrBlank()
                        ) {
                            _eventFlow.emit(PostReviewViewModel.UiEvent.ShowSnackbar("모든 칸의 내용을 채워주세요"))
                            return@launch
                        }

                        val fileList = _imageUris.map { UriUtil.toFile(context, it) }

                        /**
                         * postId입력할 때 savedStateHandle을 이용해서 넘겨준걸로 수정해야함 지금은 1임
                         */

                        postReviewUseCase(
                            ReviewBodyDTO(
                                starRating = reviewBodyDTO.value.starRating,
                                storeName = reviewBodyDTO.value.storeName,
                                content = reviewBodyDTO.value.content,
                            ), fileList, 1
                        ).collect() { it ->
                            when (it) {
                                is Resource.Success -> {
                                    it.data!!.reviewId.also { _reviewId.value = it }
                                    "리뷰글 올리기 성공 reviewId: ${it.data!!.reviewId}".log()
                                }
                                is Resource.Error -> "리뷰글 올리기 실패".log()
                                is Resource.Loading -> "리뷰글 올리는 중".log()
                            }
                        }
                        _eventFlow.emit(PostReviewViewModel.UiEvent.SaveReview)

                    } catch (e: Exception) {
                        e.printStackTrace()
                        _eventFlow.emit(
                            PostReviewViewModel.UiEvent.ShowSnackbar(
                                message = e.message ?: "리뷰글을 작성하는 동안 오류가 발생했습니다"
                            )
                        )
                    }
                }
            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveReview : UiEvent()
    }
}