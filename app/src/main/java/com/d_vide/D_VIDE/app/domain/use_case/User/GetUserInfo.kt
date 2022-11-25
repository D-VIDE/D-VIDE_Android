package com.d_vide.D_VIDE.app.domain.use_case.User

import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO
import com.d_vide.D_VIDE.app.data.storage.UserStore
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.state.UserInformation.userInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserInfo @Inject constructor(
    private val repository: UserRepository,
    private val dataStore: UserStore,
) {
    operator fun invoke(): Flow<Resource<UserDTO>> = flow {

        try {
            emit(Resource.Loading())
            val r = repository.getUser()
            when(r.code()) {
                200 -> {
                    emit(Resource.Success(r.body()!!))
                    userInfo.setByUser(r.body()!!)
                    dataStore.setUserInfo(userInfo)
                }
                else -> {
                    "use case ERROR ${r.code()}: ${r.errorBody().toString()}".log()
                }
            }

        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}