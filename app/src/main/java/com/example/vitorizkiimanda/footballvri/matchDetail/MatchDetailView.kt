package com.example.vitorizkiimanda.footballvri.matchDetail

import com.example.vitorizkiimanda.footballvri.Model.TeamResponse

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
//    fun showTeamList(data: List<Team>)
    fun getBadgeHome(data: TeamResponse)
    fun getBadgeAway(data: TeamResponse)
}