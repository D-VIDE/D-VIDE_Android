package com.d_vide.D_VIDE.app.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.d_vide.D_VIDE.app.data.remote.responseDTO.BadgeDTO
import com.d_vide.D_VIDE.app.domain.model.UserInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

val Context.dataStore by preferencesDataStore("user")

class UserStore @Inject constructor(@ApplicationContext context: Context) {
    private val store = context.dataStore

    suspend fun setUserInfo(userInfo: UserInfo) { store.edit { it[USER_INFO] = Json.encodeToString(userInfo) } }
    suspend fun setUserID(id: Long) { store.edit { it[USER_INFO] = id.toString() } }
//    suspend fun setUserEmail(email: String) { store.edit { it[USER_EMAIL] = email } }
//    suspend fun setUserNickname(email: String) { store.edit { it[USER_NICKNAME] = email } }
//    suspend fun setUserProfileImg(profileImg_url: String) { store.edit { it[USER_PRF] = profileImg_url } }
//    suspend fun setUserBadge(badge: BadgeDTO) { store.edit { it[USER_BADGE] = Json.encodeToString(badge) } }
//    suspend fun setUserFollowerCount(followerCount: Int) { store.edit { it[USER_FLR] = followerCount.toString() } }
//    suspend fun setUserFollowingCount(followingCount: Int) { store.edit { it[USER_FLG] = followingCount.toString() } }
//    suspend fun setUserCarbonAmount(amount: Int) { store.edit { it[USER_CARBON] = amount.toString() } }
//    suspend fun setUserSavedPrice(saved_price: Int) { store.edit { it[USER_SAVED_PRICE] = saved_price.toString() } }
    suspend fun setToken(token: String) { store.edit { it[USER_TOKEN] = token } }
    suspend fun setFCMToken(token: String) { store.edit { it[FCM_TOKEN] = token } }

    fun getUserInfo(): Flow<UserInfo> { return store.data.map { Json.decodeFromString(it[USER_INFO]!!) } }
    fun getUserID(): Flow<Long> { return store.data.map { (it[USER_ID]!!).toLong() } }
//    fun getUserEmail(): Flow<String> { return store.data.map { it[USER_EMAIL]!! } }
//    fun getUserNickname(): Flow<String> { return store.data.map { it[USER_NICKNAME]!! } }
//    fun getUserProfileImg(): Flow<String> { return store.data.map { it[USER_PRF]!! } }
//    fun getUserBadge(): Flow<BadgeDTO> { return store.data.map { Json.decodeFromString(it[USER_BADGE]!!) } }
//    fun setUserFollowerCount(): Flow<Int> { return store.data.map { (it[USER_FLR]!!).toInt() } }
//    fun setUserFollowingCount(): Flow<Int> { return store.data.map { (it[USER_FLG]!!).toInt() } }
//    fun setUserCarbonAmount(): Flow<Int> { return store.data.map { (it[USER_CARBON]!!).toInt() } }
//    fun setUserSavedPrice(): Flow<Int> { return store.data.map { (it[USER_SAVED_PRICE]!!).toInt() } }
    fun getToken(): Flow<String> { return store.data.map { it[USER_TOKEN]!! } }
    fun getFCMToken(): Flow<String> { return store.data.map { it[FCM_TOKEN]!! } }

    companion object {
        private val USER_INFO = stringPreferencesKey("user_info")
        private val USER_ID = stringPreferencesKey("id")
        private val USER_EMAIL = stringPreferencesKey("token")
        private val USER_NICKNAME = stringPreferencesKey("nickname")
        private val USER_BADGE = stringPreferencesKey("badge")
        private val USER_PRF = stringPreferencesKey("profile")
        private val USER_FLR = stringPreferencesKey("follower")
        private val USER_FLG = stringPreferencesKey("following")
        private val USER_CARBON = stringPreferencesKey("carbon")
        private val USER_SAVED_PRICE = stringPreferencesKey("saved_price")
        private val USER_TOKEN = stringPreferencesKey("token")
        private val FCM_TOKEN = stringPreferencesKey("fcm")
    }
}