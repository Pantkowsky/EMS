package com.pantkowski.ems

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pantkowski.features.base.navigation.FeatureNavigator
import com.pantkowski.features.base.navigation.destinations.Destination

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FeatureNavigator.launch(this, Destination.ROSTER)
        this.finish()
    }
}
