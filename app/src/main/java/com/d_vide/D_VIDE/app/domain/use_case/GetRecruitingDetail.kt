package com.d_vide.D_VIDE.app.domain.use_case

import android.util.Log
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingDetailDTO
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * 모집 글 상세 UseCase
 */

class GetRecruitingDetail @Inject constructor(
    private val repository: RecruitingRepository
) {
    operator fun invoke(postId: Long): Flow<Resource<RecruitingDetailDTO>> = flow{
        try {
            emit(Resource.Loading())
            val response = repository.getRecruitingDetail(postId)
            when(response.code()) {
                200 -> {
                    Log.d("test", response.body()!!.toString())
                    emit(Resource.Success(response.body()!!))
                }
                else -> {
                    Log.d("test", "usecase ERROR ${response.code()}: ${response.errorBody().toString()}")
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