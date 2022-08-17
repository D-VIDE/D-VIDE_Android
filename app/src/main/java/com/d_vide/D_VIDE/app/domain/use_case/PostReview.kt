package com.d_vide.D_VIDE.app.domain.use_case

import android.util.Log
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingOrderDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.ReviewBodyDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingOrderIdDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.ReviewIdDTO
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class PostReview @Inject constructor (
    val repository: RecruitingRepository
){
    operator fun invoke(reviewBody: ReviewBodyDTO, files: List<File>, postId: Int): Flow<Resource<ReviewIdDTO>> = flow{
        try {
            emit(Resource.Loading())
            val r = repository.postReview(reviewBody, files, postId)

            when(r.code()) {
                201 -> { emit(Resource.Success(r.body()!!)) }
                else -> {
                    "postReview ERROR ${r.code()}: ${r.errorBody().toString()}".log()
                }
            }

        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}
