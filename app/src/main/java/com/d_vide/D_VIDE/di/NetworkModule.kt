package com.d_vide.D_VIDE.di

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.d_vide.D_VIDE.app._constants.Const.RemoteConst.BASE_URL
import com.d_vide.D_VIDE.app.data.remote.RecruitingsApi
import com.d_vide.D_VIDE.app.data.remote.ReviewsApi
import com.d_vide.D_VIDE.app.data.remote.UserApi
import com.d_vide.D_VIDE.app.data.repository.RecruitingRepositoryImpl
import com.d_vide.D_VIDE.app.data.repository.ReviewRepositoryImpl
import com.d_vide.D_VIDE.app.data.repository.UserRepositoryImpl
import com.d_vide.D_VIDE.app.data.storage.UserStore
import com.d_vide.D_VIDE.app.data.storage.dataStore
import com.d_vide.D_VIDE.app.domain.repository.RecruitingRepository
import com.d_vide.D_VIDE.app.domain.repository.ReviewRepository
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // 인터셉터 제공
    @Provides
    @Singleton
    fun provideAuthInterceptor(
        @ApplicationContext appContext: Context,
    ): Interceptor = Interceptor { chain ->
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${
                    runBlocking { 
                        appContext.dataStore.data.map {it[stringPreferencesKey("token")]}.first() 
                    }
                }"
            )
            .build()
        return@Interceptor chain.proceed(newRequest)

    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor() { it.log() }.setLevel(HttpLoggingInterceptor.Level.BODY)


    // client 제공
    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // Retrofit 제공
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    // recruiting api 제공
    @Provides
    @Singleton
    fun provideRecruitingsApi(retrofit: Retrofit): RecruitingsApi {
        return retrofit.create(RecruitingsApi::class.java)
    }

    // user api 제공
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    // review api 제공
    @Provides
    @Singleton
    fun provideReviewsApi(retrofit: Retrofit): ReviewsApi {
        return retrofit.create(ReviewsApi::class.java)
    }

    // recruitings repository 제공
    @Provides
    @Singleton
    fun provideRecruitingsRepository(api: RecruitingsApi): RecruitingRepository {
        return RecruitingRepositoryImpl(api)
    }

    // recruitings repository 제공
    @Provides
    @Singleton
    fun provideUserRepository(store: UserStore, api: UserApi): UserRepository {
        return UserRepositoryImpl(store, api)
    }

    // recruitings repository 제공
    @Provides
    @Singleton
    fun provideReviewRepository(api: ReviewsApi): ReviewRepository {
        return ReviewRepositoryImpl(api)
    }
}