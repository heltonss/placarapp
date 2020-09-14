package com.example.placaapp.ui.game.event

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.placaapp.R
import com.example.placaapp.ui.game.hometeam.HomeTeamFragment
import com.example.placaapp.ui.utils.*
import com.example.placaapp.ui.utils.MsBroadcast
import kotlinx.android.synthetic.main.fragment_event.*


class EventFragment : Fragment() {

    // responsavel por subir o layout fragment no container
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    private fun sendEventName(){
        val intent = Intent(MsBroadcast.FILTER_EVENT)
        intent.putExtra(MsBroadcast.EVENT_NAME, inputEvent.text.toString())
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btNextStep.setOnClickListener {
            nextScreen()
        }
    }

    private fun nextScreen() {
        sendEventName()
        val ft = activity?.supportFragmentManager?.beginTransaction()
        ft?.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        ft?.replace(R.id.containerGame, HomeTeamFragment())
        ft?.addToBackStack(null)
        ft?.commit()
    }
}