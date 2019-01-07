package com.example

import com.example.model.BlackjackHand
import com.example.model.CommonResponse
import com.example.model.SuccessResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val json = """
    {
        "hidden_card": {
            "rank": "6",
            "suit": "SPADES"
        },
        "visible_cards": [
            {
                "rank": "4",
                "suit": "CLUBS"
            },
            {
                "rank": "A",
                "suit": "HEARTS"
            }
        ]
    }
""".trimIndent()

val successResponse = """
    {
        "result": 2000,
        "response": $json
    }
""".trimIndent()

val errorResponse = """
    {
        "result": 4001,
        "response": {
            "message": "An error occurred."
        }
    }
""".trimIndent()

val exceptionResponse = """
    {
        "result": 5000,
        "response": {
            "exception": "AnException"
        }
    }
""".trimIndent()

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun main(args: Array<String>) {
    val adapter = moshi.adapter(BlackjackHand::class.java)
    val hand: BlackjackHand? = adapter.fromJson(json)
    println(hand)

    handleResponse(successResponse)
    handleResponse(errorResponse)
    handleResponse(exceptionResponse)
}

fun handleResponse(responseJson: String) {
    val commonAdapter = moshi.adapter(CommonResponse::class.java)
    val commonResponse = commonAdapter.fromJson(responseJson)
    if (commonResponse?.result == 2000) {
        val successAdapter: JsonAdapter<SuccessResponse<BlackjackHand>> =
            moshi.adapter(Types.newParameterizedType(SuccessResponse::class.java, BlackjackHand::class.java))
        val successResponse = successAdapter.fromJson(responseJson)
        println("Success: $successResponse")
    } else {
        println("Error or Exception: $commonResponse")
    }
}
