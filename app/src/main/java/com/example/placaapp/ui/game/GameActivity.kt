package com.example.placaapp.ui.game

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.placaapp.R
import com.example.placaapp.ui.game.awayteam.AwayTeamFragment
import com.example.placaapp.ui.game.event.EventFragment
import com.example.placaapp.ui.game.hometeam.HomeTeamFragment
import com.example.placaapp.ui.score.ScoreActivity
import com.example.placaapp.ui.utils.*
import com.example.placaapp.ui.utils.MsBroadcast
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        showEventFragment()

        ivBack.setOnClickListener {
            onBackPressed()
        }

        registerBroadcastReceiver()
    }

    private var eventName = ""
    private var homeTeam = ""
    private var awayTeam = ""

    private fun registerBroadcastReceiver() {
        val intentFilter = IntentFilter(MsBroadcast.FILTER_EVENT)
        intentFilter.addAction(MsBroadcast.FILTER_HOME_TEAM)
        intentFilter.addAction(MsBroadcast.FILTER_AWAY_TEAM)
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, intentFilter)
    }

    public override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
    }

    private val mMessageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.hasExtra(MsBroadcast.EVENT_NAME)) {
                eventName = intent.getStringExtra(MsBroadcast.EVENT_NAME)
                showHomeTeamFragment()
            }

            if (intent.hasExtra(MsBroadcast.HOME_TEAM)) {
                homeTeam = intent.getStringExtra(MsBroadcast.HOME_TEAM)
                showAwayTeamFragment()
            }

            if (intent.hasExtra(MsBroadcast.AWAY_TEAM)) {
                awayTeam = intent.getStringExtra(MsBroadcast.AWAY_TEAM)
                showScoreActivity()
//
            }
        }
    }

    private fun showHomeTeamFragment() {
        nextFragment(HomeTeamFragment())
    }

    private fun showAwayTeamFragment() {
        nextFragment(AwayTeamFragment())
    }

    private fun nextFragment(fragment: Fragment) {
        val ft = supportFragmentManager?.beginTransaction()
        ft?.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        ft?.replace(R.id.containerGame, fragment)
        ft?.addToBackStack(null)
        ft?.commit()
    }

    private fun showScoreActivity() {
        val nextScreen = Intent(this@GameActivity, ScoreActivity::class.java)
        nextScreen.putExtra(MsBroadcast.VAR_EVENT_NAME, eventName)
        nextScreen.putExtra(MsBroadcast.VAR_HOME_TEAM, homeTeam)
        nextScreen.putExtra(MsBroadcast.VAR_AWAY_TEAM, awayTeam)
        startActivity(nextScreen)
        finish()
    }

    fun showEventFragment() {
        val ft = supportFragmentManager.beginTransaction();
        ft.add(R.id.containerGame, EventFragment())
        ft.commit()
    }
}