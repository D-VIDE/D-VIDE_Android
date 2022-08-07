package com.d_vide.D_VIDE.app.data.repository

import com.d_vide.D_VIDE.app.data.remote.RecruitingsApi
import com.d_vide.D_VIDE.app.data.remote.dto.Recruiting
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import javax.inject.Inject

class RecruitingRepositoryImpl @Inject constructor(
    private val api: RecruitingsApi
) : RecruitingRepository {

    override suspend fun getRecruitings(): List<Recruiting> {
        return api.getRecruitings()
    }
}