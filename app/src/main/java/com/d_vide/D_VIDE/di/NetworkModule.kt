package com.d_vide.D_VIDE.di

import com.d_vide.D_VIDE.app._constants.RemoteConst
import com.d_vide.D_VIDE.app.data.remote.RecruitingsApi
import com.d_vide.D_VIDE.app.data.repository.RecruitingRepositoryImpl
import com.d_vide.D_VIDE.app.domain.model.UserInfo
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RemoteConst.RemoteConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecruitingsApi(retrofit: Retrofit): RecruitingsApi {
        return retrofit.create(RecruitingsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRecruitingsRepository(api: RecruitingsApi): RecruitingRepository {
        return RecruitingRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor = Interceptor{ chain ->
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${UserInfo.GUEST.token}")
            .build()
        return@Interceptor chain.proceed(newRequest)
    }
}