package com.example.vitorizkiimanda.footballvri.model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
        @SerializedName("event") val matches: List<Match>
)