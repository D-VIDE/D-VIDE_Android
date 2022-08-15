package com.d_vide.D_VIDE.app.domain.use_case

import com.d_vide.D_VIDE.app.data.remote.requestDTO.EmailPasswordDTO
import com.d_vide.D_VIDE.app.domain.model.Token
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DoLogin @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(emailPw: EmailPasswordDTO): Flow<Resource<Token>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.doLogin(emailPw)
            when(r.code()) {
                200 -> {
                    emit(Resource.Success(r.body()!!))
                }
                else -> {
                    "usecase ERROR ${r.code()}: ${r.errorBody().toString()}".log()
                }
            }

        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}