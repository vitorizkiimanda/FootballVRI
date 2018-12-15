package com.example.vitorizkiimanda.footballvri.matches

import com.example.vitorizkiimanda.footballvri.api.ApiRepository
import com.example.vitorizkiimanda.footballvri.Model.MatchResponse
import com.example.vitorizkiimanda.footballvri.api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchesPresenter(private val view: MatchesView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson) {


    fun getLastMatches(idLeage: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLastMatches(idLeage)),
                    MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.matches)
            }
        }
    }

    fun getNextMatches(idLeage: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatches(idLeage)),
                    MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.matches)
            }
        }
    }

}