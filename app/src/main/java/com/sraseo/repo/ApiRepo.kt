package com.sraseo.repo

import com.sraseo.network.ApiService
import com.sraseo.ytclient.model.Order
import com.sraseo.ytclient.model.ReqSignup
import com.sraseo.ytclient.model.ResSignup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiRepo @Inject constructor(var apiService: ApiService) {

    fun signup(reqSignup: ReqSignup): Flow<ResSignup> {
        return  flow {
            emit(apiService.signup(reqSignup))
        }.flowOn(Dispatchers.IO)
    }

    fun getData(): Flow<Order> {
        return  flow {
            emit(apiService.getOrder())
        }.flowOn(Dispatchers.IO)
    }
}