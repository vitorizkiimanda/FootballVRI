package com.example.vitorizkiimanda.footballvri.playerDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vitorizkiimanda.footballvri.model.Player
import com.example.vitorizkiimanda.footballvri.R
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val data: Player = intent.getParcelableExtra<Player>("dataPlayer")

        //Toolbar
        supportActionBar?.title = data.strPlayer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //setData to UI
        if(data.strThumb!=null){
            Glide.with(applicationContext)
                    .load(data.strThumb)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .into(player_thumb)

        }
        player_weight.text = data.strWeight
        player_height.text = data.strHeight
        player_posisition.text = data.strPosition
        player_description.text = data.strDescriptionEN
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
