package com.example.placaapp.ui.game.hometeam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.placaapp.R
import com.example.placaapp.ui.game.awayteam.AwayTeamFragment
import com.example.placaapp.ui.utils.*
import com.example.placaapp.ui.utils.MsBroadcast
import kotlinx.android.synthetic.main.fragment_home_team.*


class HomeTeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_team, container, false)
    }

    private fun sendHomeTeamName() {
        val intent = Intent(MsBroadcast.FILTER_HOME_TEAM)
        intent.putExtra(MsBroadcast.HOME_TEAM, inputHomeTeam.text.toString())
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btNextStep.setOnClickListener {
            nextScreen()
        }
    }
    private fun nextScreen() {
        sendHomeTeamName()
    }
}