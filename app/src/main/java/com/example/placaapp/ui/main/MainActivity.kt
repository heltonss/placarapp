package com.example.placaapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.placaapp.R
import com.example.placaapp.ui.game.GameActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btNewGame.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        btExit.setOnClickListener {
            finish()
        }
    }
}