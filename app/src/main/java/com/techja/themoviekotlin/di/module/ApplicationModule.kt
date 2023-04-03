package com.techja.themoviekotlin.di.module

import com.techja.themoviekotlin.api.APIHelper
import com.techja.themoviekotlin.api.APIHelperImpl
import com.techja.themoviekotlin.api.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideAPIService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Provides
    @ViewModelScoped
    fun provideAPIHelper(apiHelper: APIHelperImpl): APIHelper = apiHelper
}