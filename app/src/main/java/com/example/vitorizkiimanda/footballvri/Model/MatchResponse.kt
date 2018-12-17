package com.example.vitorizkiimanda.footballvri.Model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
        @SerializedName("events") val matches: List<Match>
)