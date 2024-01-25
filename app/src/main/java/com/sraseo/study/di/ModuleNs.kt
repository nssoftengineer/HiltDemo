package com.sraseo.study.di


import com.sraseo.study.repo.ApiServiceNs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleNs {

    @Provides
    @Singleton
    fun getBaseUrl():String{
        return "https://jsonplaceholder.typicode.com/"
    }

    @Provides
    @Singleton
    fun getRetrofit(url:String): ApiServiceNs
    {
       return Retrofit.Builder()
           .baseUrl(url)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(ApiServiceNs::class.java)
    }
}