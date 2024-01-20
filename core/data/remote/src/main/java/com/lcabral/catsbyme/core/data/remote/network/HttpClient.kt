package com.lcabral.catsbyme.core.data.remote.network

interface HttpClient {
    fun <T> create(clazz: Class<T>): T
}
