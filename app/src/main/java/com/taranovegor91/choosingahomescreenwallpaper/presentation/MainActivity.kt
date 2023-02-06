package com.taranovegor91.choosingahomescreenwallpaper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taranovegor91.choosingahomescreenwallpaper.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ChoosingAHomeScreenWallpaper)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}