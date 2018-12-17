package com.example.vitorizkiimanda.footballvri.matches


import android.os.Bundle
import android.support.design.R.id.search_button
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*

import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.searchMatch.SearchMatchActivity
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.support.v4.intentFor

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
    private var menuItem: Menu? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_matches, container, false)

        //binding
        viewpager_matches = view.findViewById(R.id.viewpager_matches)
        tabs_matches = view.findViewById(R.id.tabs_matches)

        viewpager_matches.adapter = MatchesPagerAdapter (childFragmentManager)
        tabs_matches.setupWithViewPager(viewpager_matches)

        //show menu
        setHasOptionsMenu(true)

        return view
    }

//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        inflater!!.inflate(R.menu.search_menu_button, menu)
//        menuItem = menu
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            search_button -> {
//                startActivity(intentFor<SearchMatchActivity>())
//                true
//            }
//
//            else -> super.onOptionsItemSelected(item)
//        }
//    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.mnSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Match"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMatch(query.toString())
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean = false
        })
        super.onCreateOptionsMenu(  menu, inflater)
    }

    fun searchMatch(data: String){
        startActivity(intentFor<SearchMatchActivity>("query" to data))
    }
}

