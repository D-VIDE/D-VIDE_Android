package com.d_vide.D_VIDE.app.presentation.view.Login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.responseDTO.IdentificationDTO
import com.d_vide.D_VIDE.app.domain.use_case.Login.LoginUseCases
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KakaoLoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases,
): ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    private var identification = mutableStateOf(IdentificationDTO())
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            getUserToken()
            "LoginViewModel init 에서 확인한 토큰값 : ${identification.value.token}".log()
            if (identification.value.token.isNotBlank()) {
                _eventFlow.emit(UiEvent.Login)
            }
        }
    }

    private suspend fun loginWithKakao(token: String) {
        loginUseCases.doKakaoLogin(token).collect() {
            when (it) {
                is Resource.Success -> {
                    "userID: ${it.data!!.userId}, token: ${it.data!!.token}".log()
                    _eventFlow.emit(UiEvent.Login)
                }
                is Resource.Error -> {
                    "카카오 로그인 실패".log()
                    _eventFlow.emit(UiEvent.ERROR("로그인에 실패였습니다."))
                }
                is Resource.Loading -> "카카오 로그인 중".log()
            }
        }
    }

    private suspend fun getUserToken() {
        loginUseCases.getToken().collect() {
            identification.value.token = it
        }
    }

    suspend fun isLoggedIn(): Boolean {
        getUserToken()
        return !identification.value.token.isNullOrBlank()
    }

    fun handleKakaoLogin(context: Context) {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                "카카오계정으로 로그인 실패".log()
            } else if (token != null) {
                "카카오계정으로 로그인 성공 ${token.accessToken}".log()
                viewModelScope.launch {loginWithKakao(token.accessToken)}
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    "카카오톡으로 로그인 실패 $error".log()

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    "카카오톡으로 로그인 성공 ${token.accessToken}".log()
                    viewModelScope.launch {loginWithKakao(token.accessToken)}
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }


    public fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginByKakao -> {
                viewModelScope.launch {
                    try {
                        handleKakaoLogin(event.context)
                    } catch (e: Exception) {
                        "로그인 중 에러 발생".log()
                        _eventFlow.emit(UiEvent.ERROR(message = "로그인 중 에러 발생"))
                    }
                }
            }
            else -> {}
        }
    }

    sealed class UiEvent {
        data class ERROR(val message: String) : UiEvent()
        object Login : UiEvent()
    }
}