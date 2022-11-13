package com.d_vide.D_VIDE.app.domain.use_case

import android.util.Log
import com.d_vide.D_VIDE.app.data.remote.requestDTO.BadgeRequestDTO
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostBadge  @Inject constructor(
    val repository: UserRepository
) {
    operator fun invoke(badgeRequestDTO: BadgeRequestDTO): Flow<Resource<BadgeRequestDTO>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.postBadge(badgeRequestDTO)
            when(r.code()) {
                202 -> {
                    emit(Resource.Success(r.body()!!))
                }
                else -> { Log.d("test", r.errorBody().toString()) }
            }

        } catch(e: HttpException) {
            Log.d("test", "postRecruiting: An unexpected error occured")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            Log.d("test", "postRecruiting: Couldn't reach server. Check your internet connection.")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}