package com.example.vitorizkiimanda.footballvri.teamDetail

import com.example.vitorizkiimanda.footballvri.Model.Player
import com.example.vitorizkiimanda.footballvri.Model.fromExample.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
    fun showTeamPlayers(data: List<Player>)
}