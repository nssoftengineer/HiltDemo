package com.sraseo.module

import com.sraseo.network.ApiClient
import com.sraseo.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Network {

    @Singleton
    @Provides
    fun getApiService(apiClient: ApiClient): ApiService {
        return apiClient.create()
    }

}