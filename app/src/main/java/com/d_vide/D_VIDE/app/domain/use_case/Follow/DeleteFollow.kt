package com.d_vide.D_VIDE.app.domain.use_case.Follow

import android.util.Log
import com.d_vide.D_VIDE.app.data.remote.responseDTO.FollowIdDTO
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteFollow @Inject constructor(
    private val repository: UserRepository
){
    operator fun invoke(followIdDTO: FollowIdDTO): Flow<Resource<FollowIdDTO>> = flow {

        try {
            emit(Resource.Loading())
            val r = repository.deleteFollow(followIdDTO)
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