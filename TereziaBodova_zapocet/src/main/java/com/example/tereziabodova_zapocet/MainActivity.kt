package com.example.tereziabodova_zapocet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity()
{

    private lateinit var startNewGameButton: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)                  //specification of layout (comes to activity_main)

        startNewGameButton = findViewById(R.id.startNewGameButton)

        startNewGameButton.setOnClickListener {
            val intent = Intent( this, Activity::class.java)
            startActivity(intent)
        }
    }
}
