package com.testpressreactnativesdk

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.facebook.react.uimanager.ThemedReactContext
import com.tpstream.player.ui.TpStreamPlayerFragment
import com.tpstream.player.TpInitParams
import com.tpstream.player.ui.InitializationListener
import com.tpstream.player.TpStreamPlayer

class TPStreamPlayerView(context: ThemedReactContext) : FrameLayout(context) {

    private var playerFragment: TpStreamPlayerFragment? = null
    var videoId: String = ""
    var accessToken: String = ""

    init {
        // Ensure context is ready
        if (context.currentActivity == null) {
            // Log a message or delay execution until the context is initialized
            throw IllegalStateException("Current activity is not available yet.")
        }

        // Inflate the layout file into this view
        LayoutInflater.from(context).inflate(R.layout.tp_stream_player_view, this, true)

        // Initialize the fragment only when the activity is available
        val fragmentActivity = context.currentActivity as? FragmentActivity
        fragmentActivity?.let {
            val fragmentManager = it.supportFragmentManager
            playerFragment = fragmentManager.findFragmentById(R.id.tpstream_player_fragment) as? TpStreamPlayerFragment
                ?: TpStreamPlayerFragment()

            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.tp_stream_player_container, playerFragment!!)
            transaction.commit()
        } ?: throw IllegalStateException("Context is not of type FragmentActivity")
    }

    fun initializePlayer() {
        val params = TpInitParams.Builder()
            .setVideoId(videoId)
            .setAccessToken(accessToken)
            .build()

        playerFragment?.setOnInitializationListener(object : InitializationListener {
            override fun onInitializationSuccess(player: TpStreamPlayer) {
                player.load(params)
            }
        })
    }
}
