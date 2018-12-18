package com.example.vitorizkiimanda.footballvri.teamDetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vitorizkiimanda.footballvri.util.invisible
import com.example.vitorizkiimanda.footballvri.util.visible
import com.example.vitorizkiimanda.footballvri.adapter.PlayersAdapter
import com.example.vitorizkiimanda.footballvri.model.Player
import com.example.vitorizkiimanda.footballvri.model.fromExample.Team
import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.api.ApiRepository
import com.example.vitorizkiimanda.footballvri.database.FavouriteTeam
import com.example.vitorizkiimanda.footballvri.database.database
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var listPlayers: RecyclerView
    private var isFavorite: Boolean = false
    private lateinit var origin:String
    private var menuItem: Menu? = null
    private lateinit var id: String
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: PlayersAdapter
    private lateinit var teamData: Team
//    private lateinit var teamDataFavourite: FavouriteMatch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        //binding
        progressBar = findViewById(R.id.progress_bar)
        listPlayers = findViewById(R.id.rvTeamPlayers)

        adapter = PlayersAdapter(players)
        listPlayers.adapter = adapter

        //layout manager
        listPlayers.layoutManager = LinearLayoutManager(this)

        id = intent.getStringExtra("id")
        origin= intent.getStringExtra("origin")

        //get team detail data from API
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)

        //get players
        presenter.getTeamPlayers(id)


        //Toolbar
        supportActionBar?.title = "Team Detail"
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
            database.use {
                Log.d("checkDB", "id :" + id)
                val result = select(FavouriteTeam.TABLE_FAVORITE_TEAM)
                        .whereArgs("(TEAM_ID = {id})",
                                "id" to id)
                val favorite = result.parseList(classParser<FavouriteTeam>())
                if (!favorite.isEmpty()) isFavorite = true
            }
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
        try {

            database.use {
                insert(FavouriteTeam.TABLE_FAVORITE_TEAM,
                        FavouriteTeam.TEAM_ID to teamData.teamId,
                        FavouriteTeam.TEAM_NAME to teamData.teamName,
                        FavouriteTeam.TEAM_BADGE to teamData.teamBadge)
            }

            team_image.snackbar(R.string.toast_fav_add).show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                database.use {
                    delete(FavouriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                            "id" to id)
                }
            }
            team_image.snackbar(R.string.toast_fav_remove).show()
        } catch (e: SQLiteConstraintException){
            team_image.snackbar(e.localizedMessage).show()
        }
    }

    private fun setImage(data: String) {
        if (isFavorite) {
            showLoading()
            Glide.with(applicationContext)
                    .load(data)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(team_image)
            hideLoading()
        }
        else {
            Glide.with(applicationContext)
                    .load(data)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(team_image)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        //for SQLite
        teamData = data[0]

        setImage(data[0].teamBadge!!)

        team_name.text = data[0].teamName
        team_year.text = data[0].teamFormedYear
        team_stadium.text = data[0].teamStadium
        team_description.text = data[0].teamDescription
    }
    override fun showTeamPlayers(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
