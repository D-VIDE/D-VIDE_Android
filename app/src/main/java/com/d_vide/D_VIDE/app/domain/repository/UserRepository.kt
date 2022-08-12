package com.d_vide.D_VIDE.app.domain.repository

import com.d_vide.D_VIDE.app.domain.model.UserInfo

interface UserRepository {

    fun getLoggedInUser()

    fun setLoggedInUser(user: UserInfo)
}