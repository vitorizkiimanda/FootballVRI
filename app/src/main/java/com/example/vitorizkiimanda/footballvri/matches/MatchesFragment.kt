package com.example.vitorizkiimanda.footballvri.matches


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.vitorizkiimanda.footballvri.R
import kotlinx.android.synthetic.main.fragment_matches.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchesFragment : Fragment() {

    private lateinit var viewpager_matches: ViewPager
    private lateinit var tabs_matches: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_matches, container, false)

        //binding
        viewpager_matches = view.findViewById(R.id.viewpager_matches)
        tabs_matches = view.findViewById(R.id.tabs_matches)

        viewpager_matches.adapter = MatchesPagerAdapter (childFragmentManager)
        tabs_matches.setupWithViewPager(viewpager_matches)

        return view
    }


}

