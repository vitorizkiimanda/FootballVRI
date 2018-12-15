package com.example.vitorizkiimanda.footballvri.favourites.subFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.dicoding.kotlinacademy.util.invisible
import com.dicoding.kotlinacademy.util.visible
import com.example.vitorizkiimanda.footballvri.Adapter.FavouriteMatchesAdapter
import com.example.vitorizkiimanda.footballvri.Model.Match
import com.example.vitorizkiimanda.footballvri.database.FavouriteMatch
import com.example.vitorizkiimanda.footballvri.database.database

import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.matches.MatchesView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouritesMatchesFragment : Fragment(),MatchesView {

    private var matches: MutableList<FavouriteMatch> = mutableListOf()
    private lateinit var adapter: FavouriteMatchesAdapter
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourites_matches, container, false)


        //binding
        listMatch = view.findViewById(R.id.rvMatchesFav)
        progressBar = view.findViewById(R.id.progress_bar)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)

        adapter = FavouriteMatchesAdapter(matches)
        listMatch.adapter = adapter

        //layout manager
        listMatch.layoutManager = LinearLayoutManager(context)

        //pull to update
        swipeRefreshLayout.onRefresh {
            showFavorite()
        }

        showFavorite()
        return view
    }

    override fun showMatchList(data: List<Match>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    private fun showFavorite(){
        matches.clear()
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            showLoading()
            val result = select(FavouriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavouriteMatch>())
            matches.addAll(favorite)
            adapter.notifyDataSetChanged()
            hideLoading()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

}
