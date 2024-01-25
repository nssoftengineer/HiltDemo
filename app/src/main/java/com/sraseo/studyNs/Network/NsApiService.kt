package com.sraseo.studyNs.Network

import com.sraseo.studyNs.Model.NsData
import retrofit2.http.GET

interface NsApiService{

    @GET("posts")
    suspend fun getData():List<NsData>

}