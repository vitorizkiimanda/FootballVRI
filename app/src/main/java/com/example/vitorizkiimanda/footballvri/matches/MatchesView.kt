package com.example.vitorizkiimanda.footballvri.matches

import com.example.vitorizkiimanda.footballschedulevri.Api.Model.Match

interface MatchesView {
    fun showLoading()
    fun hideLoading()
    //    fun showTeamList(data: List<Team>)
    fun showMatchList(data: List<Match>)
}