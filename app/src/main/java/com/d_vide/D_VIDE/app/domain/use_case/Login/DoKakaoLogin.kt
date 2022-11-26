package com.d_vide.D_VIDE.app.domain.use_case.Login

import com.d_vide.D_VIDE.app.data.remote.responseDTO.IdentificationDTO
import com.d_vide.D_VIDE.app.data.storage.UserStore
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.state.UserInformation
import com.d_vide.D_VIDE.app.presentation.state.UserInformation.userInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject

class DoKakaoLogin @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(token: String): Flow<Resource<IdentificationDTO>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.doKakaoLogin(token)
            when(r.code()) {
                200 -> {
                    emit(Resource.Success(r.body()!!))
                    repository.setUserToken(r.body()!!.token)
                    repository.setUserID(r.body()!!.userId)
                    userInfo.userId = r.body()!!.userId
                    repository.setUserInfo(userInfo)
                }
                else -> {
                    "use case ERROR ${r.code()}: ${r.errorBody().toString()}".log()
                }
            }

        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}