package com.d_vide.D_VIDE.app.domain.use_case

import com.d_vide.D_VIDE.app.data.remote.dto.Recruiting
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecruitings @Inject constructor(
    private val repository: RecruitingRepository
) {
    operator fun invoke(): Flow<Resource<List<Recruiting>>> = flow {
        try {
            emit(Resource.Loading<List<Recruiting>>())
            val recruiting = repository.getRecruitings()
            emit(Resource.Success<List<Recruiting>>(recruiting))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Recruiting>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Recruiting>>("Couldn't reach server. Check your internet connection."))
        }
    }
}