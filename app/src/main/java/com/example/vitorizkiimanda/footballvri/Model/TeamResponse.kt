package com.example.vitorizkiimanda.footballvri.Model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
        @SerializedName("teams") val team: List<Team>
)