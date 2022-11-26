package com.d_vide.D_VIDE.app.presentation.view.Recruitings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.domain.use_case.GetRecruitings
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruitingsViewModel @Inject constructor(
    private val getRecruitingsUseCase: GetRecruitings
) : ViewModel() {

    private val _state = mutableStateOf(RecruitingsState())
    val state: State<RecruitingsState> = _state

    init {
        getRecruitings(37.49015482509, 127.030767490, Category.ALL, 0)
    }

    fun getRecruitings(
        latitude: Double = 37.49015482509,
        longitude: Double = 127.030767490,
        category: Category = Category.ALL,
        offset: Int = 0
    ) {
        viewModelScope.launch {
            getRecruitingsUseCase(latitude, longitude, category, offset).collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value =
                            result.data?.let { RecruitingsState(recruitingDTOS = it.recruitingDTOS) }!!
                        result.data.recruitingDTOS.toString().log()
                    }
                    is Resource.Error -> {
                        _state.value = RecruitingsState(
                            error = result.message ?: "An unexpected error occured"
                        )
                        Log.d("test", "error")
                    }
                    is Resource.Loading -> {
                        _state.value = RecruitingsState(isLoading = true)
                        Log.d("test", "loading")
                    }
                }
            }
        }
    }
}