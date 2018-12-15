package com.example.vitorizkiimanda.footballvri.matchDetail

import com.example.vitorizkiimanda.footballvri.api.ApiRepository
import com.example.vitorizkiimanda.footballvri.Model.TeamResponse
import com.example.vitorizkiimanda.footballvri.api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getTeamHome(teamName: String?) {

        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamName)),
                    TeamResponse::class.java
            )

            uiThread {
//                view.hideLoading()
                view.getBadgeHome(data)
            }
        }
    }

    fun getTeamAway(teamName: String?) {

//        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamName)),
                    TeamResponse::class.java
            )

            uiThread {
                view.getBadgeAway(data)
                view.hideLoading()
            }
        }
    }

}