package com.d_vide.D_VIDE.app.domain.use_case

import android.util.Log
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingBodyDTO
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingIdDTO
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class PostRecruiting @Inject constructor(
    val repository: RecruitingRepository
) {
    operator fun invoke(recruitingBody: RecruitingBodyDTO, files: List<File>): Flow<Resource<RecruitingIdDTO>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.postRecruiting(recruitingBody, files)
            when(r.code()) {
                201 -> {
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