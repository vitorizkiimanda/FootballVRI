package com.example.vitorizkiimanda.footballvri.teams

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<com.example.vitorizkiimanda.footballvri.model.fromExample.Team>)
}