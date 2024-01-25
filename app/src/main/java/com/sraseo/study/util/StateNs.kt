package com.sraseo.study.util

import com.sraseo.study.module.PostNs


sealed class StateNs {
    object Loading : StateNs()
    class Failure(val msg: Throwable) : StateNs()
    class Success(val data: List<PostNs>) : StateNs()
    object Empty : StateNs()
}



