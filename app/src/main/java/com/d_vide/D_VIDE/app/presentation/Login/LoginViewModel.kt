package com.d_vide.D_VIDE.app.presentation.Login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.IdentificationDTO
import com.d_vide.D_VIDE.app.domain.use_case.Login.LoginUseCases
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
    private val loginUseCases: LoginUseCases,
) : ViewModel() {

    private var _user = mutableStateOf(IdentificationDTO())
    val user: State<IdentificationDTO> = _user

    private var _emailPw = mutableStateOf(
        EmailPasswordDTO(
        // TODO: 나중에 지웁시다
        email = "email@gmail.com",
        password = "password1"
    )
    )
    val emailPw: State<EmailPasswordDTO> = _emailPw

    private var identification = mutableStateOf(IdentificationDTO())

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            getUserToken()
            "LoginViewModel init 에서 확인한 토큰값 : ${identification.value.token}".log()
            "LoginViewModel init 에서 확인한 ID 값 : ${identification.value.userId}".log()
            if (!identification.value.token.isNullOrBlank()) {
                loginUseCases.setToken(identification.value.token)
                _eventFlow.emit(UiEvent.Login)
            }
        }
    }

    private suspend fun login() {
        loginUseCases.doLogin(_emailPw.value).collectLatest {
            when (it) {
                is Resource.Success -> {
                    identification.value = it.data!!

                    "LoginViewModel에서 확인한 토큰값 : ${identification.value.token}".log()
                    "LoginViewModel에서 확인한 ID 값 : ${identification.value.userId}".log()
                    loginUseCases.setToken(identification.value.token)
                    loginUseCases.setUserID(identification.value.userId)
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

    private suspend fun getUserToken() {
        loginUseCases.getToken().collect() {
            identification.value.token = it
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
        return !identification.value.token.isNullOrBlank()
    }

    sealed class UiEvent {
        data class Error(val message: String) : UiEvent()
        object Login : UiEvent()
    }
}