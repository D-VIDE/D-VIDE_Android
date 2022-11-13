package com.d_vide.D_VIDE.app.domain.use_case.Login

import com.d_vide.D_VIDE.app.data.remote.requestDTO.FcmTokenDTO
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.log
import javax.inject.Inject

class PostFCMToken @Inject constructor(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(fcmTokenDTO: FcmTokenDTO) {
        "POSTING 하는 중 $fcmTokenDTO".log()
        repository.postFCMToken(fcmTokenDTO)
    }
}