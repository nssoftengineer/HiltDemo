package com.sraseo.studyNs.Util

import com.sraseo.studyNs.Model.NsData

sealed class NsApiState {
    object Loading : NsApiState()
    class Success(val data: List<NsData>) : NsApiState()
    class Failure(val throwable: Throwable) : NsApiState()
    object Empty : NsApiState()
}