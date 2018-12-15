package com.example.vitorizkiimanda.footballvri.Model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
        @SerializedName("player") val players: List<Player>
)