package com.spaceflight.network.exception

class NoBodyException : Exception()

class RequestFailureException(s: String) : Exception(s)

class HTTPNetworkException() : Exception()