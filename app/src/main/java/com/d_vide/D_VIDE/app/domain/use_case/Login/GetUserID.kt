package com.d_vide.D_VIDE.app.domain.use_case.Login

import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserID @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(): Flow<Long> = flow {
        try {
            emit(repository.getUserID())
        } catch(e: Exception) {
            "ERROR: use case GetUserID form dataStore".log()
            emit(-1)
        }
    }
}