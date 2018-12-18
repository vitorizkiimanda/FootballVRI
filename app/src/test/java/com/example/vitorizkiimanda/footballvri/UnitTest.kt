package com.example.vitorizkiimanda.footballvri

import com.example.vitorizkiimanda.footballvri.Model.Team
import com.example.vitorizkiimanda.footballvri.Model.TeamResponse
import com.example.vitorizkiimanda.footballvri.api.ApiRepository
import com.example.vitorizkiimanda.footballvri.api.TheSportDBApi
import com.example.vitorizkiimanda.footballvri.matchDetail.MatchDetailPresenter
import com.example.vitorizkiimanda.footballvri.matchDetail.MatchDetailView
import com.example.vitorizkiimanda.footballvri.util.formatDate
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UnitTest {
    @Test
    fun testDoRequest() {
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }

    @Test
    fun formatDate() {
        val date = ("2018-12-10")
        Assert.assertEquals("Mon, 10 Dec 2018", formatDate(date))
    }

    //MatchDetailPresenter
    @Mock
    private
    lateinit var view: MatchDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson)
    }

    @Test
    fun testGetTeamHome() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val teamName = "Everton"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamName)),
                    TeamResponse::class.java
            )).thenReturn(response)

            presenter.getTeamHome(teamName)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).getBadgeHome(response)
            Mockito.verify(view).hideLoading()
        }
    }
}

//unit test :
//
//1. formatDate =  melakukan cek pada fungsi formator tanggal pertandingan
//
//usecase : "2018-12-10"
//
//keluaran yang benar : "Mon, 10 Dec 2018"

//2. testGetTeamHome = Mocking MatchDetailPresenter
//
//Mengecek apakah presenter dapat bekerja sesuai harapan
//
//usecase : nama team ="Everton"
//
//mocking parameter yang dibutuhkan , pangggilan API, dan view