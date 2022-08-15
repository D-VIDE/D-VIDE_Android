package com.d_vide.D_VIDE.app.domain.use_case

import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingOrderDTO
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingOrderIdDTO
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostRecruitingOrder @Inject constructor (
    val repository: RecruitingRepository
) {
    operator fun invoke(recruitingOrder: RecruitingOrderDTO): Flow<Resource<RecruitingOrderIdDTO>> = flow{
        try {
            emit(Resource.Loading())
            val r = repository.postRecruitingOrder(recruitingOrder)
            when(r.code()) {
                200 -> { emit(Resource.Success(r.body()!!)) }
                else -> {
                    "postRecruitingOrder ERROR ${r.code()}: ${r.errorBody().toString()}".log()
                }
            }

        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}