package com.example.placaapp.ui.game.awayteam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.placaapp.R
import com.example.placaapp.ui.score.ScoreActivity
import com.example.placaapp.ui.utils.*
import com.example.placaapp.ui.utils.MsBroadcast
import kotlinx.android.synthetic.main.fragment_away_team.*


class AwayTeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_away_team, container, false)
    }

    private fun sendAwayTeamName() {
        val intent = Intent(MsBroadcast.FILTER_AWAY_TEAM)
        intent.putExtra(MsBroadcast.AWAY_TEAM, inputAwayTeam.text.toString())
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btNextStep.setOnClickListener {
            sendAwayTeamName()
        }
    }
    private fun nextScreen() {
        sendAwayTeamName()
        val nextScreen = Intent(activity, ScoreActivity::class.java)
        startActivity(nextScreen)
        activity?.finish()
    }
}