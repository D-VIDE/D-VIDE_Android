package com.d_vide.D_VIDE.app.data.repository

import com.d_vide.D_VIDE.app.data.storage.UserInfoStore
import com.d_vide.D_VIDE.app.domain.model.UserInfo
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val store: UserInfoStore
) : UserRepository {
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun getLoggedInUser() {
        return runBlocking {
            store.getLoggedInUser().first()
        }
    }

    override fun setLoggedInUser(user: UserInfo) {
        scope.launch {
            store.setLoggedInUser(user)
        }
    }
}