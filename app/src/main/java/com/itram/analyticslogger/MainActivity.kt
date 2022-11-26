package com.itram.analyticslogger

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(),
    AnalyticsLogger by AnalyticsLoggerImpl(),
    DeepLinkHandler by DeepLinkHandlerImpl() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerLifeCycleOwner(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeepLink(this, intent)
    }
}

interface DeepLinkHandler {
    fun handleDeepLink(activity: AppCompatActivity, intent: Intent?)
}

class DeepLinkHandlerImpl : DeepLinkHandler {
    override fun handleDeepLink(activity: AppCompatActivity, intent: Intent?) {

    }

}

interface AnalyticsLogger {
    fun registerLifeCycleOwner(owner: LifecycleOwner)
}

class AnalyticsLoggerImpl : AnalyticsLogger, LifecycleEventObserver {
    override fun registerLifeCycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> println("User opened the screen")
            Lifecycle.Event.ON_PAUSE -> println("User opened the screen")
            else -> Unit
        }
    }
}