package com.sraseo.studyNs.Repo

import com.sraseo.studyNs.Model.NsData
import com.sraseo.studyNs.Network.NsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NsRepo @Inject constructor(var nsApiService: NsApiService) {

    suspend fun getData():Flow<List<NsData>>
    {
      return  flow {
            emit(nsApiService.getData())
        }.flowOn(Dispatchers.IO)
    }
}