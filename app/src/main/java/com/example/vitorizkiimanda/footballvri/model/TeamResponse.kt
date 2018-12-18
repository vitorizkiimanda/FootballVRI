package com.example.vitorizkiimanda.footballvri.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
        @SerializedName("teams") val team: List<Team>
)