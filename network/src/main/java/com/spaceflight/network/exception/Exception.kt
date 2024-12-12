package com.spaceflight.network.exception

class NoBodyException : Exception()

class RequestFailureException(message: String) : Exception(message)

class HTTPNetworkException(message: String) : Exception(message)