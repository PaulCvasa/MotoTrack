package com.example.mototrack

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

    fun virtualGarage(view: View)
    {
        val intent = Intent(this, VirtualGarage::class.java)
        startActivity(intent)
        finish()
    }

    fun logout(view: View)
    {
        FirebaseAuth.getInstance().signOut()
        val intent2 = Intent(this, MainActivityScreen::class.java)
        finish()
    }
    fun addMotorcycle(view: View)
    {
        val intent3 = Intent(this, AddMotorcycle::class.java)
        startActivity(intent3)
        finish()
    }
}