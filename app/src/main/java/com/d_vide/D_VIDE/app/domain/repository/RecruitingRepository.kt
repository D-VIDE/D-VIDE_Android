package com.d_vide.D_VIDE.app.domain.repository

import com.d_vide.D_VIDE.app.data.remote.dto.Recruiting

interface RecruitingRepository {

    suspend fun getRecruitings(): List<Recruiting>
}