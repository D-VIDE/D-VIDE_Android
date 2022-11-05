package com.d_vide.D_VIDE.app.domain.use_case

import com.d_vide.D_VIDE.app.data.remote.responseDTO.FollowInfoDTO
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetFollowInformation @Inject constructor(
    val repository: UserRepository
) {
    operator fun invoke(relation: String, offset: Int): Flow<Resource<FollowInfoDTO>> = flow{
        try{
            emit(Resource.Loading())
            val r = repository.getFollowInformation(relation, offset)
            when(r.code()) {
                200 -> emit(Resource.Success(r.body()!!))
                else -> r.errorBody().toString().log()
            }
        }
        catch(e: HttpException) {
            "An unexpected error occured".log()
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            "Couldn't reach server. Check your internet connection".log()
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}