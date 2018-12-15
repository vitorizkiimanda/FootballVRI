package com.example.vitorizkiimanda.footballvri.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vitorizkiimanda.footballvri.Model.Player
import com.example.vitorizkiimanda.footballvri.R
import com.example.vitorizkiimanda.footballvri.playerDetail.PlayerDetailActivity
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.startActivity

class PlayersAdapter (private val players: List<Player>)
    : RecyclerView.Adapter<PlayerViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {
        val viewHolder = PlayerViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.item_player, p0, false))

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            p0.context?.startActivity<PlayerDetailActivity>("dataPlayer" to players[position])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return players.size
    }


    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position])
    }

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerCutout = view.player_cutout
    private val playerName = view.player_name
    private val playerPosition = view.player_posisiton

    fun bindItem(players: Player) {
        Glide.with(itemView.context)
                .load(players.strCutout)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(playerCutout)
        playerName?.text = players.strPlayer
        playerPosition?.text = players.strPosition
    }
}