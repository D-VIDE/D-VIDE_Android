package com.d_vide.D_VIDE.di

import com.d_vide.D_VIDE.app.data.repository.UserRepositoryImpl
import com.d_vide.D_VIDE.app.data.storage.UserInfoStore
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideUserRepository(store: UserInfoStore): UserRepository {
        return UserRepositoryImpl(store)
    }
}