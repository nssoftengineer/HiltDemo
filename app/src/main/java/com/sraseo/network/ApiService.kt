package com.sraseo.network

import com.sraseo.ytclient.model.Order
import com.sraseo.ytclient.model.ReqSignup
import com.sraseo.ytclient.model.ResSignup
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("signup")
    suspend fun signup(@Body reqSignup: ReqSignup): ResSignup

    @GET("getorderdetail1")
    suspend fun getOrder(): Order
}