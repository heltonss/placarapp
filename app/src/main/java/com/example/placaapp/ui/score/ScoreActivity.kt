package com.example.placaapp.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.placaapp.R
import com.example.placaapp.ui.utils.MsBroadcast
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        setExtras()
    }

    private fun setExtras() {
        tvEventName.text = intent.extras?.getString(MsBroadcast.VAR_EVENT_NAME)
        tvAwayTeam.text = intent.extras?.getString(MsBroadcast.VAR_AWAY_TEAM)
        tvHomeTeam.text = intent.extras?.getString(MsBroadcast.VAR_HOME_TEAM)
    }
}