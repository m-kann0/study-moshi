package com.example.model

data class CommonResponse(
    val result: Int,
    val response: CommonResponseContent
) {
    data class CommonResponseContent(val message: String?, val exception: String?)
}
