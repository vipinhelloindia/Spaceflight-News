package com.spaceflight.common

sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()

    class Failure(val throwable: Throwable) : Resource<Nothing>()
}