package com.example.vitorizkiimanda.footballvri.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vitorizkiimanda.footballvri.util.formatDate
import com.example.vitorizkiimanda.footballvri.database.FavouriteMatch
import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.matchDetail.MatchDetailActivity
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.startActivity

class FavouriteMatchesAdapter (private val matches: List<FavouriteMatch>)
    : RecyclerView.Adapter<FavouriteMatchViewHolder>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavouriteMatchViewHolder {
        val viewHolder = FavouriteMatchViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_match, p0, false))

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            p0.context?.startActivity<MatchDetailActivity>("eventId" to matches[position].eventId, "origin" to "favourite")
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return matches.size
    }
    override fun onBindViewHolder(p0: FavouriteMatchViewHolder, p1: Int) {
        p0.bindItem(matches[p1])
    }

}

class FavouriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val nameHome = view.name_home
    private val nameAway = view.name_away
    private val scoreHome = view.score_home
    private val scoreAway = view.score_away
    private val matchDate = view.match_date

    fun bindItem(matches: FavouriteMatch) {
//        this.matches = matches
        nameHome?.text = matches.homeName
        nameAway?.text = matches.awayName
        scoreHome?.text = matches.homeScore
        scoreAway?.text = matches.awayScore
        matchDate?.text = formatDate(matches.eventDate)
    }
}