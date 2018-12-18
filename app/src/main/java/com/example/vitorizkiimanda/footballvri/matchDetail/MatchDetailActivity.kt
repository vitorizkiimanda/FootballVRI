package com.example.vitorizkiimanda.footballvri.matchDetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vitorizkiimanda.footballvri.util.formatDate
import com.example.vitorizkiimanda.footballvri.util.invisible
import com.example.vitorizkiimanda.footballvri.util.visible
import com.example.vitorizkiimanda.footballvri.api.ApiRepository
import com.example.vitorizkiimanda.footballvri.model.Match
import com.example.vitorizkiimanda.footballvri.model.TeamResponse
import com.example.vitorizkiimanda.footballvri.database.FavouriteMatch
import com.example.vitorizkiimanda.footballvri.database.database
import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.R.drawable.ic_add_to_favorites
import com.example.vitorizkiimanda.footballvri.R.drawable.ic_added_to_favorites
import com.example.vitorizkiimanda.footballvri.R.id.add_to_favorite
import com.example.vitorizkiimanda.footballvri.R.menu.detail_menu
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var progressBar: ProgressBar
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var matchData: Match
    private lateinit var matchDataFavourite: FavouriteMatch
    private lateinit var origin:String
    private lateinit var eventId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)


        //binding
        progressBar = findViewById(R.id.progress_bar)

        origin= intent.getStringExtra("origin")
        if(origin == "favourite"){
            eventId = intent.getStringExtra("eventId")

            //get Match Data drom SQLite
            database.use {
                showLoading()
                database.use {
                    val result  = select("TABLE_FAVORITE_MATCH")
                            .whereArgs("(EVENT_ID = {eventId})",
                                    "eventId" to eventId)

                    val dataList = result.parseList(classParser<FavouriteMatch>())
                    val data = dataList[0]

                    matchDataFavourite = data
                    match_date.text = data.eventDate

                    name_home.text = data.homeName
                    score_home.text = data.homeScore
                    goals_home.text = data.homeGoals
                    keeper_home.text = data.homeKeeper
                    defense_home.text = data.homeDefense
                    midfield_home.text = data.homeMidfield
                    forward_home.text = data.homeForward
                    subs_home.text = data.homeSubs
                    shots_home.text = data.homeShots

                    name_away.text = data.awayName
                    score_away.text = data.awayScore
                    goals_away.text = data.awayGoals
                    keeper_away.text = data.awayKeeper
                    defense_away.text = data.awayDefense
                    midfield_away.text = data.awayMidfield
                    forward_away.text = data.awayForward
                    subs_away.text = data.awaySubs
                    shots_away.text = data.awayShots
                }
            }

            //get picture home
            val request = ApiRepository()
            val gson = Gson()
            presenter = MatchDetailPresenter(this, request, gson)
            presenter.getTeamHome(matchDataFavourite.homeName)


            //get picture away
            presenter.getTeamAway(matchDataFavourite.awayName)
            hideLoading()
        }
        else {
            val data: Match = intent.getParcelableExtra<Match>("match")
            matchData = data

            match_date.text = formatDate(data.dateEvent)

            name_home.text = data.strHomeTeam
            score_home.text = data.intHomeScore
            goals_home.text = data.strHomeGoalDetails
            keeper_home.text = data.strHomeLineupGoalkeeper
            defense_home.text = data.strHomeLineupDefense
            midfield_home.text = data.strHomeLineupMidfield
            forward_home.text = data.strHomeLineupForward
            subs_home.text = data.strHomeLineupSubstitutes
            shots_home.text = data.intHomeShots

            name_away.text = data.strAwayTeam
            score_away.text = data.intAwayScore
            goals_away.text = data.strAwayGoalDetails
            keeper_away.text = data.strAwayLineupGoalkeeper
            defense_away.text = data.strAwayLineupDefense
            midfield_away.text = data.strAwayLineupMidfield
            forward_away.text = data.strAwayLineupForward
            subs_away.text = data.strAwayLineupSubstitutes
            shots_away.text = data.intAwayShots

            //get picture home
            val request = ApiRepository()
            val gson = Gson()
            presenter = MatchDetailPresenter(this, request, gson)
            presenter.getTeamHome(data.strHomeTeam)


            //get picture away
            presenter.getTeamAway(data.strAwayTeam)

        }

        //Toolbar
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun getBadgeHome(data: TeamResponse) {

        Glide.with(applicationContext)
                .load(data.team[0].strTeamBadge)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(image_home)
    }

    override fun getBadgeAway(data: TeamResponse) {
        Glide.with(applicationContext)
                .load(data.team[0].strTeamBadge)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(image_away)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    private fun favoriteState(){
        if(origin == "favourite"){
            isFavorite = true
        }
        else {
            database.use {
                Log.d("checkDB", "id :" + matchData.idEvent)
                val result = select(FavouriteMatch.TABLE_FAVORITE_MATCH)
                        .whereArgs("(EVENT_ID = {id})",
                                "id" to matchData.idEvent)
                val favorite = result.parseList(classParser<FavouriteMatch>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        }
        setFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        //check favourite status
        favoriteState()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun addToFavorite(){
        try {

            if(origin == "favourite"){
                database.use {
                    insert(FavouriteMatch.TABLE_FAVORITE_MATCH,
                            FavouriteMatch.EVENT_ID to matchDataFavourite.eventId,
                            FavouriteMatch.EVENT_DATE to matchDataFavourite.eventDate,
                            FavouriteMatch.HOME_ID to matchDataFavourite.homeId,
                            FavouriteMatch.HOME_NAME to matchDataFavourite.homeName,
                            FavouriteMatch.HOME_SCORE to matchDataFavourite.homeScore,
                            FavouriteMatch.HOME_GOALS to matchDataFavourite.homeGoals,
                            FavouriteMatch.HOME_SHOTS to matchDataFavourite.homeShots,
                            FavouriteMatch.HOME_KEEPER to matchDataFavourite.homeKeeper,
                            FavouriteMatch.HOME_DEFENSE to matchDataFavourite.homeDefense,
                            FavouriteMatch.HOME_MIDFIELD to matchDataFavourite.homeMidfield,
                            FavouriteMatch.HOME_FORWARD to matchDataFavourite.homeForward,
                            FavouriteMatch.HOME_SUBS to matchDataFavourite.homeSubs,
                            FavouriteMatch.AWAY_ID to matchDataFavourite.awayId,
                            FavouriteMatch.AWAY_NAME to matchDataFavourite.awayName,
                            FavouriteMatch.AWAY_SCORE to matchDataFavourite.awayScore,
                            FavouriteMatch.AWAY_GOALS to matchDataFavourite.awayGoals,
                            FavouriteMatch.AWAY_SHOTS to matchDataFavourite.awayShots,
                            FavouriteMatch.AWAY_KEEPER to matchDataFavourite.awayKeeper,
                            FavouriteMatch.AWAY_DEFENSE to matchDataFavourite.awayDefense,
                            FavouriteMatch.AWAY_MIDFIELD to matchDataFavourite.awayMidfield,
                            FavouriteMatch.AWAY_FORWARD to matchDataFavourite.awayForward,
                            FavouriteMatch.AWAY_SUBS to matchDataFavourite.awaySubs)
                }
            }
            else{
                database.use {
                    insert(FavouriteMatch.TABLE_FAVORITE_MATCH,
                            FavouriteMatch.EVENT_ID to matchData.idEvent,
                            FavouriteMatch.EVENT_DATE to matchData.dateEvent,
                            FavouriteMatch.HOME_ID to matchData.idHomeTeam,
                            FavouriteMatch.HOME_NAME to matchData.strHomeTeam,
                            FavouriteMatch.HOME_SCORE to matchData.intHomeScore,
                            FavouriteMatch.HOME_GOALS to matchData.strHomeGoalDetails,
                            FavouriteMatch.HOME_SHOTS to matchData.intHomeShots,
                            FavouriteMatch.HOME_KEEPER to matchData.strHomeLineupGoalkeeper,
                            FavouriteMatch.HOME_DEFENSE to matchData.strHomeLineupDefense,
                            FavouriteMatch.HOME_MIDFIELD to matchData.strHomeLineupMidfield,
                            FavouriteMatch.HOME_FORWARD to matchData.strHomeLineupForward,
                            FavouriteMatch.HOME_SUBS to matchData.strHomeLineupSubstitutes,
                            FavouriteMatch.AWAY_ID to matchData.idAwayTeam,
                            FavouriteMatch.AWAY_NAME to matchData.strAwayTeam,
                            FavouriteMatch.AWAY_SCORE to matchData.intAwayScore,
                            FavouriteMatch.AWAY_GOALS to matchData.strAwayGoalDetails,
                            FavouriteMatch.AWAY_SHOTS to matchData.intAwayShots,
                            FavouriteMatch.AWAY_KEEPER to matchData.strAwayLineupGoalkeeper,
                            FavouriteMatch.AWAY_DEFENSE to matchData.strAwayLineupDefense,
                            FavouriteMatch.AWAY_MIDFIELD to matchData.strAwayLineupMidfield,
                            FavouriteMatch.AWAY_FORWARD to matchData.strAwayLineupForward,
                            FavouriteMatch.AWAY_SUBS to matchData.strAwayLineupSubstitutes)
                }
            }
            match_date.snackbar(R.string.toast_fav_add).show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            if(origin == "favourite"){
                database.use {
                    database.use {
                        delete(FavouriteMatch.TABLE_FAVORITE_MATCH, "(EVENT_ID = {id})",
                                "id" to eventId)
                    }
                }
            }
            else {
                database.use {
                    delete(FavouriteMatch.TABLE_FAVORITE_MATCH, "(EVENT_ID = {id})",
                            "id" to matchData.idEvent)
                }
            }
            match_date.snackbar(R.string.toast_fav_remove).show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }


    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

}