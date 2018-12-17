package com.example.vitorizkiimanda.footballvri.matches.subFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.vitorizkiimanda.footballvri.util.invisible
import com.example.vitorizkiimanda.footballvri.util.visible
import com.example.vitorizkiimanda.footballvri.Adapter.MatchAdapter
import com.example.vitorizkiimanda.footballvri.api.ApiRepository
import com.example.vitorizkiimanda.footballvri.Model.Match

import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.matches.MatchesPresenter
import com.example.vitorizkiimanda.footballvri.matches.MatchesView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.onRefresh

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchesLastFragment : Fragment(), MatchesView {

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchesPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_matches_last, container, false)

        //binding
        listMatch = view.findViewById(R.id.rvMatchesLast)
        progressBar = view.findViewById(R.id.progress_bar)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
        spinner = view.findViewById(R.id.spinnerLeagues)

        //spinner
        leagueName = "English Premier League"
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                getData(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        adapter = MatchAdapter(matches)
        listMatch.adapter = adapter

        //layout manager
        listMatch.layoutManager = LinearLayoutManager(context)

        //pull to update
        swipeRefreshLayout.onRefresh {
            getData(leagueName)
        }
        getData(leagueName)

        return view
    }


    fun getData(data: String){
        //id
        var id = "4328"
        when (data) {
            "English Premier League" -> {
                id = "4328"
            }
            "English League Championship" -> {
                id = "4329"
            }
            "German Bundesliga" -> {
                id = "4331"
            }
            "Italian Serie A" -> {
                id = "4332"
            }
            "French Ligue 1" -> {
                id = "4334"
            }
            "Spanish La Liga" -> {
                id = "4335"
            }
        }

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchesPresenter(this, request, gson)
        presenter.getLastMatches(id)
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

}
