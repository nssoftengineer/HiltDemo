package com.sraseo.study.repo


import com.sraseo.study.module.PostNs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class RepoNs @Inject constructor(var apiService: ApiServiceNs) {

    suspend fun getData(): Flow<List<PostNs>>
    {
       return flow {
            emit(apiService.getPost())
        }.flowOn(Dispatchers.IO)
    }
}