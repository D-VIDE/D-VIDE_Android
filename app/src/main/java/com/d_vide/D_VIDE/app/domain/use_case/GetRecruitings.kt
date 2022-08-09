package com.d_vide.D_VIDE.app.domain.use_case

import android.util.Log
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingsDTO
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecruitings @Inject constructor(
    private val repository: RecruitingRepository,
) {
    operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<RecruitingsDTO>> = flow {

        try {
            emit(Resource.Loading())
            val r = repository.getRecruitings(latitude, longitude)
            when(r.code()) {
                200 -> {
                    Log.d("test", r.body()!!.toString())
                    emit(Resource.Success(r.body()!!))
                }
                else -> {
                    Log.d("test", r.errorBody().toString())
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