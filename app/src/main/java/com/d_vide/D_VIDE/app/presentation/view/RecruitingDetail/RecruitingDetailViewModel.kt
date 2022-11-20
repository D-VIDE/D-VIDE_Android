package com.d_vide.D_VIDE.app.presentation.view.RecruitingDetail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.GetRecruitingDetail
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.presentation.view.Recruitings.RecruitingsState
import com.d_vide.D_VIDE.app.presentation.navigation.DetailDestinationKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecruitingDetailViewModel @Inject constructor(
    val getRecruitingDetailUseCase: GetRecruitingDetail,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    val postId = savedStateHandle.get<Long>(DetailDestinationKey.RECRUITING)!!

    private var _recruitingDetail = mutableStateOf(recruitingDetailState(isLoading = true))
    var recruitingDetail: State<recruitingDetailState> = _recruitingDetail

    init{
        getRecruitingDetail(postId)
    }

    private fun getRecruitingDetail(postId: Long){
        viewModelScope.launch {
            getRecruitingDetailUseCase(postId).collect{ result ->

                when(result){
                    is Resource.Success -> {
                        _recruitingDetail.value = result.data?.let {
                            recruitingDetailState(recruitingDetail = it.recruitingDetail, isLoading = false)
                        }!!
                    }
                    is Resource.Error -> {
                        _recruitingDetail.value = recruitingDetailState(error = result.message!!)
                        Log.d("test", "error")
                    }
                    is Resource.Loading -> {
                        _recruitingDetail.value = recruitingDetailState(isLoading = true)
                        Log.d("test", "loading")
                    }
                }
            }
        }
    }

}