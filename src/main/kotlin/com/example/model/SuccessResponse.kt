package com.example.model

data class SuccessResponse<T>(
    val result: Int,
    val response: T
)
