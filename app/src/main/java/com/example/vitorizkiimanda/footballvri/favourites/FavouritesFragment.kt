package com.example.vitorizkiimanda.footballvri.favourites


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.matches.MatchesPagerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouritesFragment : Fragment() {

    private lateinit var viewpager_favourites: ViewPager
    private lateinit var tabs_favourites: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)

        //binding
        viewpager_favourites = view.findViewById(R.id.viewpager_favourites)
        tabs_favourites = view.findViewById(R.id.tabs_favourites)

        viewpager_favourites.adapter = FavouritesPagerAdapter(childFragmentManager)
        tabs_favourites.setupWithViewPager(viewpager_favourites)

        return view
    }


}
