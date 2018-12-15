package com.example.vitorizkiimanda.footballvri.matches

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.vitorizkiimanda.footballvri.matches.subFragments.MatchesLastFragment
import com.example.vitorizkiimanda.footballvri.matches.subFragments.MatchesNextFragment

class MatchesPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MatchesNextFragment()
            }
            else -> {
                return MatchesLastFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Next"
            else -> {
                return "last"
            }
        }
    }

}