package com.d_vide.D_VIDE.app.domain.use_case

import android.util.Log
import com.d_vide.D_VIDE.app.data.remote.responseDTO.FollowIdDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.UserIdDTO
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostFollow @Inject constructor(
    private val repository: UserRepository
){
    operator fun invoke(userIdDTO: UserIdDTO): Flow<Resource<FollowIdDTO>> = flow {

        try {
            emit(Resource.Loading())
            val r = repository.postFollow(userIdDTO)
            when(r.code()) {
                201 -> {
                    Log.d("test", r.body()!!.toString())
                    emit(Resource.Success(r.body()!!))
                }
                else -> {
                    Log.d("test", "usecase ERROR ${r.code()}: ${r.errorBody().toString()}")
                }
            }

        } catch(e: HttpException) {
            Log.d("qwer", "An unexpected error occured")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            Log.d("qwer", "Couldn't reach server. Check your internet connection.")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}