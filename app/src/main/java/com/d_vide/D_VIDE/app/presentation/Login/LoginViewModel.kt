package com.d_vide.D_VIDE.app.presentation.Login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.FcmTokenDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO
import com.d_vide.D_VIDE.app.domain.model.Token
import com.d_vide.D_VIDE.app.domain.use_case.UserUseCases
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases

) : ViewModel() {

    private var _user = mutableStateOf(UserDTO())
    val user: State<UserDTO> = _user

    private var _emailPw = mutableStateOf(
        EmailPasswordDTO(
        // TODO: 나중에 지웁시다
        email = "email@gmail.com",
        password = "password1"
    )
    )
    val emailPw: State<EmailPasswordDTO> = _emailPw

    private var token = mutableStateOf(Token())
    private var fcm = mutableStateOf(FcmTokenDTO())

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            getUserToken()
            getFCMToken()
            "LoginViewModel init에서 확인한 토큰값 : ${token.value.value}".log()
            if (!token.value.value.isNullOrBlank()) {
                userUseCases.setToken(token.value)
                _eventFlow.emit(UiEvent.Login)
            }
            "FCM 값은 ${fcm.value.fcmToken}".log()
            if (fcm.value.fcmToken.isNotBlank()){
                "FCM in 로그인 화면 ${fcm.value}".log()
                userUseCases.postFCMToken(fcm.value)
            }
        }
    }

    private suspend fun login() {
        userUseCases.doLogin(_emailPw.value).collectLatest {
            when (it) {
                is Resource.Success -> {
                    token.value = it.data!!
                    userUseCases.setToken(token.value)
                    "LoginViewModel에서 확인한 토큰값 : ${token.value.value}".log()
                    _eventFlow.emit(UiEvent.Login)
                }
                is Resource.Error -> {
                    "로그인 중 에러 발생".log()
                    _eventFlow.emit(UiEvent.Error("cannot login"))
                }
                is Resource.Loading -> {
                    "토큰 값 가져오는 중...".log()
                }
            }
        }
    }

    suspend fun getUserInfo() {
        userUseCases.getUserInfo().collect() {
            when (it) {
                is Resource.Success -> {
                    _user.value = it.data!!
                }
                is Resource.Error -> {
                    "유저 정보 가져오는 중 에러 발생".log()
                }
                is Resource.Loading -> {
                    "유저 정보 가져오는 중...".log()
                }
            }
        }
    }

    private suspend fun getUserToken() {
        userUseCases.getToken().collect() {
            token.value.value = it.value
        }
    }
    private suspend fun getFCMToken(){
        userUseCases.getFCMToken().collect() {
            fcm.value.fcmToken = it.fcmToken
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _emailPw.value = emailPw.value.copy(
                    email = event.value
                )
            }
            is LoginEvent.EnteredPassword -> {
                _emailPw.value = emailPw.value.copy(
                    password = event.value
                )
            }
            is LoginEvent.Login -> {
                viewModelScope.launch {
                    try {
                        if (emailPw.value.email.isNullOrBlank()
                            || emailPw.value.password.isNullOrBlank()
                        ) {
                            "ERROR 입력 되지 않은 칸이 존재".log()
                            _eventFlow.emit(UiEvent.Error(message = "모든 칸의 내용을 채워주세요"))
                            return@launch
                        }
                        login()
                    } catch (e: Exception) {
                        "로그인 중 에러 발생".log()
                        _eventFlow.emit(UiEvent.Error(message = "로그인 중 에러 발생"))
                    }
                }
            }
        }

    }

    suspend fun isLoggedIn(): Boolean {
        getUserToken()
        return !token.value.value.isNullOrBlank()
    }

    sealed class UiEvent {
        data class Error(val message: String) : UiEvent()
        object Login : UiEvent()
    }
}