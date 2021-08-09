package com.pantkowski.features.roster

import com.pantkowski.features.base.activities.BaseActivity
import org.koin.core.module.Module

class RosterActivity : BaseActivity(R.layout.activity_roster) {

    override val hostId: Int
        get() = R.id.rosterHost

    override val modules: List<Module>
        get() = rosterModules
}
