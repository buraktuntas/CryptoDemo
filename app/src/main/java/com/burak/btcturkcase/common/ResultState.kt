package com.burak.btcturkcase.common

sealed class ResultState<out T> {

    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error<out T>(val exception: Throwable) : ResultState<T>()
    object Complete : ResultState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Complete -> "Complete"
        }
    }
}

