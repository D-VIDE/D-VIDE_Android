package com.d_vide.D_VIDE.app.data.repository

import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.data.remote.RecruitingsApi
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingBodyDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingIdDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingOrderDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingOrderIdDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingsDTO
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class RecruitingRepositoryImpl @Inject constructor(
    private val api: RecruitingsApi
) : RecruitingRepository {

    override suspend fun getRecruitings(
        latitude: Double,
        longitude: Double,
        category: Category,
        offset: Int
    ): Response<RecruitingsDTO> {
        return api.getRecruitings(latitude, longitude, category.tag, offset)
    }

    override suspend fun postRecruiting(recruitingBody: RecruitingBodyDTO, files: List<File>): Response<RecruitingIdDTO> {
        val multipartBodyList = arrayListOf<MultipartBody.Part>()
        files.forEachIndexed { index, file ->
            multipartBodyList.add(
                MultipartBody.Part.createFormData(
                    name = "postImgFiles",
                    filename = file.name,
                    body = file.asRequestBody("image/*".toMediaType())
                )
            )
        }
        return api.postRecruiting(
            request = Gson().toJson(recruitingBody)
                .toRequestBody("application/json".toMediaTypeOrNull()),
            images = multipartBodyList
        )
    }

    override suspend fun postRecruitingOrder(recruitingOrder: RecruitingOrderDTO, files: List<File>): Response<RecruitingOrderIdDTO> {
        val multipartBodyList = arrayListOf<MultipartBody.Part>()
        files.forEachIndexed{ index, file ->
            multipartBodyList.add(
                MultipartBody.Part.createFormData(
                    name = "orderImgFiles",
                    filename = file.name,
                    body = file.asRequestBody("image/*".toMediaType())
                )
            )
        }
        return api.postRecruitingOrder(
            request = Gson().toJson(recruitingOrder)
                .toRequestBody("application/json".toMediaTypeOrNull()),
            images = multipartBodyList
        )
    }
}