package com.example.vitorizkiimanda.footballvri.playerDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.vitorizkiimanda.footballvri.Model.Match
import com.example.vitorizkiimanda.footballvri.Model.Player
import com.example.vitorizkiimanda.footballvri.R

class PlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val data: Player = intent.getParcelableExtra<Player>("dataPlayer")

        //Toolbar
        supportActionBar?.title = data.strPlayer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
