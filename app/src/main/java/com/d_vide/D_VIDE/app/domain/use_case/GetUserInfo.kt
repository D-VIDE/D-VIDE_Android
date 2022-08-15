package com.d_vide.D_VIDE.app.domain.use_case

import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserInfo @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(): Flow<Resource<UserDTO>> = flow {

        try {
            emit(Resource.Loading())
            val r = repository.getUserInfo()
            when(r.code()) {
                200 -> { emit(Resource.Success(r.body()!!)) }
                else -> {
                    "usecase ERROR ${r.code()}: ${r.errorBody().toString()}".log()
                }
            }

        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}