package com.Alkemy.alkemybankbase.utils

import com.Alkemy.alkemybankbase.data.remote.ApiService
import com.Alkemy.alkemybankbase.repository.*
import com.Alkemy.alkemybankbase.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  AppModule {
    @Singleton
    @Provides
    fun provideLoginRepository(apiService: ApiService) = LoginRepository(apiService) as LoginRepo

    @Singleton
    @Provides
    fun provideSignUpRepository(apiService: ApiService) = SignUpRepository(apiService) as SignUpRepo

    @Singleton
    @Provides
    fun provideCargaRepository(apiService: ApiService) = CargaRepository(apiService) as CargaRepo

    @Singleton
    @Provides
    fun provideEnviarRepository(apiService: ApiService) = EnviarRepository(apiService) as EnviarRepo

    @Singleton
    @Provides
    fun provideGastosRepository(apiService: ApiService) = GastosRepository(apiService) as GastosRepo

    @Singleton
    @Provides
    fun provideApiService() : ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)
    }
}