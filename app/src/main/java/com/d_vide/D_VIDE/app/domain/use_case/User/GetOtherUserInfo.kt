package com.d_vide.D_VIDE.app.domain.use_case.User

import com.d_vide.D_VIDE.app.data.remote.responseDTO.OtherUserInfoDTO
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetOtherUserInfo @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(userId: Long): Flow<Resource<OtherUserInfoDTO>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.getOtherUserInfo(userId)
            when(r.code()) {
                200 -> { emit(Resource.Success(r.body()!!)) }
                else -> {
                    emit(Resource.Error("getOtherUserInfo failed"))
                    "getOtherUserInfo usecase ERROR ${r.code()}: ${r.errorBody().toString()}".log()
                }
            }

        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}