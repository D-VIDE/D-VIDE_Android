package com.d_vide.D_VIDE.app.data.fcm

import android.content.Intent
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.d_vide.D_VIDE.MainActivity
import com.d_vide.D_VIDE.app.data.remote.requestDTO.FcmTokenDTO
import com.d_vide.D_VIDE.app.data.storage.dataStore
import com.d_vide.D_VIDE.app.domain.use_case.Login.LoginUseCases
import com.d_vide.D_VIDE.app.domain.util.log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessageService : FirebaseMessagingService() {
    @Inject
    lateinit var loginUseCases: LoginUseCases

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.data.isNotEmpty()){
            "Message data payload: ${message.data}".log()
        }
        message.notification?.let {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            "Message Notification Title: ${it.title}".log()
            "Message Notification Body: ${it.body}".log()
        }


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                "Fetching FCM registration token failed ${task.exception}".log()
                return@OnCompleteListener
            }
            val token = task.result
            "onMessageReceived ${token}".log()
        })
    }
    override fun onNewToken(token: String) {
        // sendRegistrationToServer(token)
        "onNewToken $token".log()
        GlobalScope.launch {
            val fcmTokenDTO = FcmTokenDTO(token)
            loginUseCases.setFCMToken(fcmTokenDTO.fcmToken)
            loginUseCases.postFCMToken(fcmTokenDTO)
            "onNewToken에서 Save 하는 중".log()
            saveGCMToken(token)
        }
    }

    // Save GCM Token DataStore Preference
    private suspend fun saveGCMToken(token: String) {
        "saveGCMToken ${token}".log()
        val gckTokenKey = stringPreferencesKey("gcm_token")
        baseContext.dataStore.edit { pref ->
            pref[gckTokenKey] = token
        }
    }
}