package com.project.whattowatch

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.project.whattowatch.common.di.initDependencyInjection
import org.jetbrains.anko.AnkoLogger

class WhatToWatch : MultiDexApplication() {

    init {
        instance = this
    }

    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        initDependencyInjection(this)
        super.onCreate()
    }

    private var mCurrentActivity: Activity? = null
    fun getCurrentActivity(): Activity? {
        return mCurrentActivity
    }

    fun setCurrentActivity(mCurrentActivity: Activity?) {
        this.mCurrentActivity = mCurrentActivity
    }

    companion object : AnkoLogger {
        private var instance: WhatToWatch? = null

        override val loggerTag: String
            get() = "WhatToWatch"

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}