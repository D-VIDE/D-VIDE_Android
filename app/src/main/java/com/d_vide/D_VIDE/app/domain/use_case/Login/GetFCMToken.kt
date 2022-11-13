package com.d_vide.D_VIDE.app.domain.use_case.Login

import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFCMToken @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(): Flow<String> = flow {
        try {
            emit(repository.getFCMToken())
        } catch(e: Exception) {
            "ERROR: use case GetFCMToken form dataStore".log()
            emit("")
        }
    }
}