package com.pantkowski.features.base.navigation

import android.content.Context
import android.content.Intent
import com.pantkowski.features.base.navigation.destinations.Destination

object FeatureNavigator {

    fun launch(context: Context, destination: Destination) =
        when(destination) {
            Destination.ROSTER -> launchFeature(context, Action.LAUNCH_ROSTER)
        }

    private fun launchFeature(context: Context, action: String) =
        context.startActivity(createIntent(context, action))

    private fun createIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}
