package com.d_vide.D_VIDE.app.presentation.view.Recruitings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingsDTO
import com.d_vide.D_VIDE.app.domain.use_case.GetRecruitings
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruitingsViewModel @Inject constructor(
    private val getRecruitingsUseCase: GetRecruitings
) : ViewModel() {

    var state = MutableStateFlow(RecruitingsState())
        private set

    var recruitings = MutableStateFlow<List<RecruitingDTO>>(emptyList())
        private set

    var offset = MutableStateFlow<Int>(0)
        private set
    var endReached = MutableStateFlow<Boolean>(false)
        private set
    var pagingLoading = MutableStateFlow<Boolean>(false)
        private set

    init {
        getRecruitings(37.49015482509, 127.030767490, Category.ALL)
    }

    fun getRecruitings(
        latitude: Double = 37.49015482509,
        longitude: Double = 127.030767490,
        category: Category = Category.ALL
    ) {
        viewModelScope.launch {
            getRecruitingsUseCase(latitude, longitude, category, offset.value).collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        recruitings.value = recruitings.value + (result.data?.recruitingDTOS ?: emptyList())
                        state.update {
                            it.copy(
                                recruitingDTOS = it.recruitingDTOS + result.data!!.recruitingDTOS
                            )
                        }

                        offset.value += result.data?.recruitingDTOS?.size ?: 0
                        endReached.value= false
                        pagingLoading.value = false
                        Log.d("가희", "호출 ${recruitings.value.size}")
                        //result.data.recruitingDTOS.toString().log()

                    }
                    is Resource.Error -> {
                        state.value = RecruitingsState(
                            error = result.message ?: "An unexpected error occured"
                        )
                        endReached.value = true
                        Log.d("test", "error")
                    }
                    is Resource.Loading -> {
                        state.value = RecruitingsState(isLoading = true)
                        pagingLoading.value = true
                        Log.d("test", "loading")
                    }
                }
            }
        }
    }
}