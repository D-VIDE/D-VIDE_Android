package com.d_vide.D_VIDE.di

import com.d_vide.D_VIDE.app._constants.RemoteConst
import com.d_vide.D_VIDE.app.data.remote.RecruitingsApi
import com.d_vide.D_VIDE.app.data.repository.RecruitingRepositoryImpl
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecruitingsApi(): RecruitingsApi {
        return Retrofit.Builder()
            .baseUrl(RemoteConst.RemoteConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecruitingsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: RecruitingsApi): RecruitingRepository {
        return RecruitingRepositoryImpl(api)
    }
}