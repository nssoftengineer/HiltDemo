package com.sraseo.studyNs.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sraseo.studyNs.Repo.NsRepo
import com.sraseo.studyNs.Util.NsApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NsViewModel @Inject constructor(var nsRepo: NsRepo) : ViewModel() {
    var nsApiState: MutableStateFlow<NsApiState> = MutableStateFlow(NsApiState.Empty)

    fun getData(): Flow<NsApiState> {
        nsApiState.value = NsApiState.Loading
        viewModelScope.launch {
            nsRepo.getData().catch {
                nsApiState.value = NsApiState.Failure(it)
            }.collect {
                nsApiState.value = NsApiState.Success(it)
            }
        }
        return nsApiState
    }
}