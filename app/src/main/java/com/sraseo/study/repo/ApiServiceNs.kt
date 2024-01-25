package com.sraseo.study.repo

import com.sraseo.study.module.PostNs
import retrofit2.http.GET

interface ApiServiceNs {

    @GET("posts")
   suspend fun getPost():List<PostNs>
}