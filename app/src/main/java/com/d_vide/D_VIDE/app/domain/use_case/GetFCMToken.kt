package com.d_vide.D_VIDE.app.domain.use_case

import com.d_vide.D_VIDE.app.data.remote.requestDTO.FcmTokenDTO
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFCMToken @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(): Flow<FcmTokenDTO> = flow {
        try {
            emit(repository.getFCMToken())
        } catch(e: Exception) {
            "ERRROR: usecase GetToken form dataStore".log()
            emit(FcmTokenDTO(""))
        }
    }
}