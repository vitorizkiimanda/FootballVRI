package com.example.vitorizkiimanda.footballvri.teamDetail

import android.util.Log
import com.example.vitorizkiimanda.footballvri.Model.PlayerResponse
import com.example.vitorizkiimanda.footballvri.Model.fromExample.TeamResponse
import com.example.vitorizkiimanda.footballvri.api.ApiRepository
import com.example.vitorizkiimanda.footballvri.api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                    TeamResponse::class.java
            )

            uiThread {
                view.showTeamDetail(data.teams)
                view.hideLoading()
            }
        }
    }

    fun getTeamPlayers(teamId: String) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamPlayers(teamId)),
                    PlayerResponse::class.java
            )

            uiThread {
                view.showTeamPlayers(data.players)
                view.hideLoading()
            }
        }
    }
}