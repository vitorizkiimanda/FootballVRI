package com.example.vitorizkiimanda.footballvri.teamDetail

import com.example.vitorizkiimanda.footballvri.model.Player
import com.example.vitorizkiimanda.footballvri.model.fromExample.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
    fun showTeamPlayers(data: List<Player>)
}