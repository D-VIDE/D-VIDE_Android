package com.d_vide.D_VIDE.app.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.d_vide.D_VIDE.app.domain.model.UserInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore("user")

class UserInfoStore @Inject constructor(@ApplicationContext context: Context){
    private val store = context.dataStore

    suspend fun setLoggedInUser(user: UserInfo) {
        store.edit {
            it[USER_TOKEN] = user.token
            it[USER_ID] = user.id
            it[USER_EMAIL] = user.email
            it[USER_PASSWORD] = user.password
            it[USER_PROFILE_IMG] = user.profileImgUrl
            it[USER_NICKNAME] = user.nickName
            it[USER_BADGES] = user.badges!!
            it[USER_FOLLOWERS] = user.followerCount.toString()
            it[USER_FOLLOWINGS] = user.followingCount.toString()
        }
    }

    fun getLoggedInUser() : Flow<UserInfo> {
        return store.data.map {
            val token =  it[USER_TOKEN] ?: UserInfo.GUEST.token
            val id = it[USER_ID] ?: UserInfo.GUEST.id
            val email = it[USER_EMAIL] ?: UserInfo.GUEST.email
            val password = it[USER_PASSWORD] ?: UserInfo.GUEST.password
            val profileImgUrl = it[USER_PROFILE_IMG] ?: UserInfo.GUEST.profileImgUrl
            val nickName = it[USER_NICKNAME] ?: UserInfo.GUEST.nickName
            val badges = it[USER_BADGES] ?: UserInfo.GUEST.badges
            val followerCount = it[USER_FOLLOWERS] ?: UserInfo.GUEST.followerCount
            val followingCount = it[USER_FOLLOWINGS] ?: UserInfo.GUEST.followingCount

            if (token == UserInfo.GUEST.token)
                UserInfo.GUEST
            else
                UserInfo(
                    token = token,
                    id = id,
                    email = email,
                    password = password,
                    profileImgUrl = profileImgUrl,
                    nickName = nickName,
                    badges = badges,
                    followerCount = followerCount as Int,
                    followingCount = followingCount as Int,

                )
        }
    }

    // 키값 정의
    companion object {
        private val USER_TOKEN = stringPreferencesKey("user_token")
        private val USER_ID = stringPreferencesKey("user_id")
        private val USER_EMAIL = stringPreferencesKey("user_email")
        private val USER_PASSWORD = stringPreferencesKey("user_password")
        private val USER_PROFILE_IMG = stringPreferencesKey("user_profile_img")
        private val USER_NICKNAME = stringPreferencesKey("user_nickname")
        private val USER_BADGES = stringPreferencesKey("user_badges")
        private val USER_FOLLOWERS = stringPreferencesKey("user_followers")
        private val USER_FOLLOWINGS = stringPreferencesKey("user_followings")
    }
}