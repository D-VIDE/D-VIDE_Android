package com.d_vide.D_VIDE.app.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.d_vide.D_VIDE.app.domain.model.Token
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore("user")

class TokenStore @Inject constructor(@ApplicationContext context: Context) {
    private val store = context.dataStore

    suspend fun setToken(token: Token) {
        store.edit {
            it[USER_TOKEN] = token.value
        }
    }

    fun getToken(): Flow<Token> {
        return store.data.map {
            Token(it[USER_TOKEN]!!)
        }
    }

    companion object {
        private val USER_TOKEN = stringPreferencesKey("user")
    }
}