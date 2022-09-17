package com.d_vide.D_VIDE.app.domain.use_case.Review

import android.util.Log
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.RecommendStores
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.ReviewsDTO
import com.d_vide.D_VIDE.app.domain.repository.ReviewRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMyReviews @Inject constructor(
    private val repository: ReviewRepository,
) {
    operator fun invoke(first: Int): Flow<Resource<ReviewsDTO>> = flow {

        try {
            emit(Resource.Loading())
            val r = repository.getMyReviews(0)
            when(r.code()) {
                200 -> {
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