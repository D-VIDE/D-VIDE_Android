package com.d_vide.D_VIDE.app.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.d_vide.D_VIDE.app.data.remote.requestDTO.FcmTokenDTO
import com.d_vide.D_VIDE.app.domain.model.Token
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.fcmStore by preferencesDataStore("fcm")

class FCMTokenStore @Inject constructor(@ApplicationContext context: Context) {
    private val store = context.fcmStore

    suspend fun setFCMToken(fcmTokenDTO: FcmTokenDTO) {
        store.edit {
            it[FCM_TOKEN] = fcmTokenDTO.fcmToken
        }
    }

    fun getFCMToken(): Flow<FcmTokenDTO> {
        return store.data.map {
            FcmTokenDTO(it[FCM_TOKEN]!!)
        }
    }

    companion object {
        private val FCM_TOKEN = stringPreferencesKey("fcm")
    }
}