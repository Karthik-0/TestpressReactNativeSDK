package com.testpressreactnativesdk

import android.content.Context
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.facebook.react.uimanager.ThemedReactContext
import com.tpstream.player.TpStreamPlayerFragment

class TPStreamPlayerView(context: ThemedReactContext) : FrameLayout(context) {

    private val playerFragment: TpStreamPlayerFragment

    init {
        val activity = context.currentActivity as FragmentActivity
        val fragmentManager = activity.supportFragmentManager
        playerFragment = TpStreamPlayerFragment()

        // Add the fragment to this view
        val transaction = fragmentManager.beginTransaction()
        transaction.add(id, playerFragment)
        transaction.commit()
    }

    fun initializePlayer(videoId: String, accessToken: String) {
        val params = TpInitParams.Builder()
            .setVideoId(videoId)
            .setAccessToken(accessToken)
            .build()
        playerFragment.setOnInitializationListener { player ->
            player.load(params)
        }
    }
}
