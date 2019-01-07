package com.example.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlackjackHand(
    @Json(name = "hidden_card") val hiddenCard: Card,
    @Json(name = "visible_cards") val visibleCards: List<Card>
)
