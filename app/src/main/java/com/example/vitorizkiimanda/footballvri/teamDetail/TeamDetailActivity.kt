package com.example.vitorizkiimanda.footballvri.teamDetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.database.FavouriteMatch
import com.example.vitorizkiimanda.footballvri.database.database
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity() {
    private var isFavorite: Boolean = false
    private lateinit var origin:String
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)


        origin= intent.getStringExtra("origin")

        //Toolbar
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
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
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        if(origin == "favourite"){
            isFavorite = true
        }
        else {
//            database.use {
//                Log.d("checkDB", "id :" + matchData.idEvent)
//                val result = select(FavouriteMatch.TABLE_FAVORITE_MATCH)
//                        .whereArgs("(EVENT_ID = {id})",
//                                "id" to matchData.idEvent)
//                val favorite = result.parseList(classParser<FavouriteMatch>())
//                if (!favorite.isEmpty()) isFavorite = true
//            }
        }
        setFavorite()
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun addToFavorite(){
//        try {
//
//            if(origin == "favourite"){
//                database.use {
//                    insert(FavouriteMatch.TABLE_FAVORITE_MATCH,
//                            FavouriteMatch.EVENT_ID to matchDataFavourite.eventId,
//                            FavouriteMatch.EVENT_DATE to matchDataFavourite.eventDate,
//                            FavouriteMatch.HOME_ID to matchDataFavourite.homeId,
//                            FavouriteMatch.HOME_NAME to matchDataFavourite.homeName,
//                            FavouriteMatch.HOME_SCORE to matchDataFavourite.homeScore,
//                            FavouriteMatch.HOME_GOALS to matchDataFavourite.homeGoals,
//                            FavouriteMatch.HOME_SHOTS to matchDataFavourite.homeShots,
//                            FavouriteMatch.HOME_KEEPER to matchDataFavourite.homeKeeper,
//                            FavouriteMatch.HOME_DEFENSE to matchDataFavourite.homeDefense,
//                            FavouriteMatch.HOME_MIDFIELD to matchDataFavourite.homeMidfield,
//                            FavouriteMatch.HOME_FORWARD to matchDataFavourite.homeForward,
//                            FavouriteMatch.HOME_SUBS to matchDataFavourite.homeSubs,
//                            FavouriteMatch.AWAY_ID to matchDataFavourite.awayId,
//                            FavouriteMatch.AWAY_NAME to matchDataFavourite.awayName,
//                            FavouriteMatch.AWAY_SCORE to matchDataFavourite.awayScore,
//                            FavouriteMatch.AWAY_GOALS to matchDataFavourite.awayGoals,
//                            FavouriteMatch.AWAY_SHOTS to matchDataFavourite.awayShots,
//                            FavouriteMatch.AWAY_KEEPER to matchDataFavourite.awayKeeper,
//                            FavouriteMatch.AWAY_DEFENSE to matchDataFavourite.awayDefense,
//                            FavouriteMatch.AWAY_MIDFIELD to matchDataFavourite.awayMidfield,
//                            FavouriteMatch.AWAY_FORWARD to matchDataFavourite.awayForward,
//                            FavouriteMatch.AWAY_SUBS to matchDataFavourite.awaySubs)
//                }
//            }
//            else{
//                database.use {
//                    insert(FavouriteMatch.TABLE_FAVORITE_MATCH,
//                            FavouriteMatch.EVENT_ID to matchData.idEvent,
//                            FavouriteMatch.EVENT_DATE to matchData.dateEvent,
//                            FavouriteMatch.HOME_ID to matchData.idHomeTeam,
//                            FavouriteMatch.HOME_NAME to matchData.strHomeTeam,
//                            FavouriteMatch.HOME_SCORE to matchData.intHomeScore,
//                            FavouriteMatch.HOME_GOALS to matchData.strHomeGoalDetails,
//                            FavouriteMatch.HOME_SHOTS to matchData.intHomeShots,
//                            FavouriteMatch.HOME_KEEPER to matchData.strHomeLineupGoalkeeper,
//                            FavouriteMatch.HOME_DEFENSE to matchData.strHomeLineupDefense,
//                            FavouriteMatch.HOME_MIDFIELD to matchData.strHomeLineupMidfield,
//                            FavouriteMatch.HOME_FORWARD to matchData.strHomeLineupForward,
//                            FavouriteMatch.HOME_SUBS to matchData.strHomeLineupSubstitutes,
//                            FavouriteMatch.AWAY_ID to matchData.idAwayTeam,
//                            FavouriteMatch.AWAY_NAME to matchData.strAwayTeam,
//                            FavouriteMatch.AWAY_SCORE to matchData.intAwayScore,
//                            FavouriteMatch.AWAY_GOALS to matchData.strAwayGoalDetails,
//                            FavouriteMatch.AWAY_SHOTS to matchData.intAwayShots,
//                            FavouriteMatch.AWAY_KEEPER to matchData.strAwayLineupGoalkeeper,
//                            FavouriteMatch.AWAY_DEFENSE to matchData.strAwayLineupDefense,
//                            FavouriteMatch.AWAY_MIDFIELD to matchData.strAwayLineupMidfield,
//                            FavouriteMatch.AWAY_FORWARD to matchData.strAwayLineupForward,
//                            FavouriteMatch.AWAY_SUBS to matchData.strAwayLineupSubstitutes)
//                }
//            }
//            match_date.snackbar(R.string.toast_fav_add).show()
//        } catch (e: SQLiteConstraintException){
//            toast(e.localizedMessage)
//        }
        team_image.snackbar(R.string.toast_fav_add).show()
    }

    private fun removeFromFavorite(){
//        try {
//            if(origin == "favourite"){
//                database.use {
//                    database.use {
//                        delete(FavouriteMatch.TABLE_FAVORITE_MATCH, "(EVENT_ID = {id})",
//                                "id" to eventId)
//                    }
//                }
//            }
//            else {
//                database.use {
//                    delete(FavouriteMatch.TABLE_FAVORITE_MATCH, "(EVENT_ID = {id})",
//                            "id" to matchData.idEvent)
//                }
//            }
//            team_image.snackbar(R.string.toast_fav_remove).show()
//        } catch (e: SQLiteConstraintException){
//            toast(e.localizedMessage)
//        }
        team_image.snackbar(R.string.toast_fav_remove).show()
    }
}
