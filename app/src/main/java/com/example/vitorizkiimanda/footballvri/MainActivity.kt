package com.example.vitorizkiimanda.footballvri

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import com.example.vitorizkiimanda.footballvri.R.id.*
import com.example.vitorizkiimanda.footballvri.favourites.FavouritesFragment
import com.example.vitorizkiimanda.footballvri.matches.MatchesFragment
import com.example.vitorizkiimanda.footballvri.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                navigation_matches -> {
                    loadMatchesFragment(savedInstanceState)
                }
                navigation_teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                navigation_favourites -> {
                    loadFavouritesFragment(savedInstanceState)
                }
            }
            true
        }
        navigation.selectedItemId = navigation_matches
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchesFragment(), MatchesFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavouritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavouritesFragment(), FavouritesFragment::class.simpleName)
                    .commit()
        }
    }
}
