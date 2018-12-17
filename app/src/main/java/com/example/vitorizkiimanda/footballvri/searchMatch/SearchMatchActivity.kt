package com.example.vitorizkiimanda.footballvri.searchMatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.dicoding.kotlinacademy.util.invisible
import com.dicoding.kotlinacademy.util.visible
import com.example.vitorizkiimanda.footballvri.Adapter.MatchAdapter
import com.example.vitorizkiimanda.footballvri.Model.Match
import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.api.ApiRepository
import com.example.vitorizkiimanda.footballvri.matches.MatchesPresenter
import com.example.vitorizkiimanda.footballvri.matches.MatchesView
import com.google.gson.Gson
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast

class SearchMatchActivity : AppCompatActivity(), MatchesView {

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchesPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var eventName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        eventName = intent.getStringExtra("query")

        //binding
        listMatch = this.findViewById(R.id.rvMatchesLast)
        progressBar = this.findViewById(R.id.progress_bar)
        swipeRefreshLayout = this.findViewById(R.id.swipe_refresh_layout)

        adapter = MatchAdapter(matches)
        listMatch.adapter = adapter

        //layout manager
        listMatch.layoutManager = LinearLayoutManager(this)

        //pull to update
        swipeRefreshLayout.onRefresh {
            getData(eventName)
        }
        getData(eventName)


        //Toolbar
        supportActionBar?.title = "Search Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun getData(data: String){
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchesPresenter(this, request, gson)
        presenter.getEventByName(data)
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showMatchList(data: List<Match>) {
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?) : Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.mnSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Match"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getData(query.toString())
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean = false
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
