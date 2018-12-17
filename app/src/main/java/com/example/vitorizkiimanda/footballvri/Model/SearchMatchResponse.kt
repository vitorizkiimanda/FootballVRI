package com.example.vitorizkiimanda.footballvri.Model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
        @SerializedName("event") val matches: List<Match>
)