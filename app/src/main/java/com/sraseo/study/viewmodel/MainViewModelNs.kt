package com.sraseo.study.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sraseo.study.repo.RepoNs
import com.sraseo.study.util.StateNs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModelNs @Inject constructor(var repoNs: RepoNs) :ViewModel() {

    var apiState: MutableStateFlow<StateNs> = MutableStateFlow(StateNs.Empty)

    fun getData(): Flow<StateNs> {
        apiState.value=StateNs.Loading
        viewModelScope.launch {
            repoNs.getData().catch {
                apiState.value=StateNs.Failure(it)
            }.collect{
                apiState.value=StateNs.Success(it)
            }
        }
        return apiState
    }
}