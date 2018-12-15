package com.example.vitorizkiimanda.footballvri.teams

import com.example.vitorizkiimanda.footballvri.Model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<com.example.vitorizkiimanda.footballvri.Model.fromExample.Team>)
}