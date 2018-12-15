package com.example.vitorizkiimanda.footballschedulevri.Api.Model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
        @SerializedName("events") val matches: List<Match>
)