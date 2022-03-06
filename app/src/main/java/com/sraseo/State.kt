package com.sraseo

sealed class State {
    object Loading : State()
    class Success(val data: Any) : State()
    class Failure(val throwable: Throwable) : State()
    object Empty : State()
}