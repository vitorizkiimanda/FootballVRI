package com.example.vitorizkiimanda.footballvri.favourites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.vitorizkiimanda.footballvri.favourites.subFragments.FavouritesMatchesFragment
import com.example.vitorizkiimanda.footballvri.favourites.subFragments.FavouritesTeamsFragment
import com.example.vitorizkiimanda.footballvri.matches.subFragments.MatchesLastFragment
import com.example.vitorizkiimanda.footballvri.matches.subFragments.MatchesNextFragment

class FavouritesPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavouritesMatchesFragment()
            }
            else -> {
                return FavouritesTeamsFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Matches"
            else -> {
                return "Teams"
            }
        }
    }

}