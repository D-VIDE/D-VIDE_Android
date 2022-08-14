package com.d_vide.D_VIDE.app.domain.use_case

import com.d_vide.D_VIDE.app.domain.model.Token
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetToken @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(): Flow<Token> = flow {
        try {
            emit(repository.getUserToken())
        } catch(e: Exception) {
            "ERRROR: usecase GetToken form dataStore".log()
            emit(Token(""))
        }
    }
}