package com.d_vide.D_VIDE.app.domain.use_case.Login

import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import javax.inject.Inject

class SetToken @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(token: String) {
        repository.setUserToken(token)
    }
}