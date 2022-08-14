package com.d_vide.D_VIDE.app.domain.use_case

import com.d_vide.D_VIDE.app.domain.model.Token
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import javax.inject.Inject

class SetToken @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(token: Token) {
        repository.setUserToken(token)
    }
}