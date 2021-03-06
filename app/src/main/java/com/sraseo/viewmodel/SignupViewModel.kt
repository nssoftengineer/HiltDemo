package com.sraseo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sraseo.State
import com.sraseo.repo.ApiRepo
import com.sraseo.ytclient.model.ReqSignup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(var apiRepo: ApiRepo) : ViewModel() {
    var apiState: MutableStateFlow<State> = MutableStateFlow(State.Empty)
    var apiStateAsLiveData: MutableLiveData<State> = MutableLiveData()

    fun getData(): MutableStateFlow<State> {
        apiState.value = State.Loading
        viewModelScope.launch {
            apiRepo.getData().catch { e ->
                apiState.value = State.Failure(e)
            }.collect {
                apiState.value = State.Success(it)
            }
        }
        return apiState
    }

    fun signUp(reqSignup: ReqSignup): LiveData<State> {
        apiStateAsLiveData.value = State.Loading
        viewModelScope.launch {
            apiRepo.signup(reqSignup).catch { e ->
                apiStateAsLiveData.value = State.Failure(e)
            }.collect {
                apiStateAsLiveData.value = State.Success(it)
            }
        }
        return apiStateAsLiveData
    }
}