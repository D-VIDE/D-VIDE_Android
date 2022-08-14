package com.d_vide.D_VIDE.app.data.repository

import com.d_vide.D_VIDE.app.data.remote.UserApi
import com.d_vide.D_VIDE.app.data.remote.dto.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.dto.UserDTO
import com.d_vide.D_VIDE.app.data.storage.TokenStore
import com.d_vide.D_VIDE.app.domain.model.Token
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val store: TokenStore,
    private val api: UserApi
) : UserRepository {
    private val scope = CoroutineScope(Dispatchers.IO)
    override suspend fun doLogin(emailPw: EmailPasswordDTO): Response<Token> {
        return api.login(emailPw)
    }

    override suspend fun getUserInfo(): Response<UserDTO> {
        return api.getUserInfo()
    }

    override fun getUserToken(): Token {
        return runBlocking {
            store.getToken().first()
        }
    }

    override fun setUserToken(token: Token) {
        scope.launch {
            store.setToken(token)
        }
    }
}