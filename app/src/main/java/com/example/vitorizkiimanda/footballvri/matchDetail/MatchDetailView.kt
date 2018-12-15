package com.example.vitorizkiimanda.footballvri.matchDetail

import com.example.vitorizkiimanda.footballschedulevri.Api.Model.TeamResponse

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
//    fun showTeamList(data: List<Team>)
    fun getBadgeHome(data: TeamResponse)
    fun getBadgeAway(data: TeamResponse)
}