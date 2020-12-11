package com.cassiano.myapplication.home.model

import com.cassiano.myapplication.internal.RequestStatus

class DataResult<T>(
    val status: RequestStatus,
    val data: T? = null
)