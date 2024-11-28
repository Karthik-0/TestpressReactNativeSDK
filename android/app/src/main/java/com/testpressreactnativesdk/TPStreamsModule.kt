package com.testpressreactnativesdk

import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.tpstream.player.TPStreamsSDK
import com.facebook.react.bridge.Callback
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.bridge.LifecycleEventListener
import androidx.fragment.app.FragmentActivity

class TPStreamsModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String = "TPStreams"

    var playerView: TPStreamPlayerView? = null

    init {
        TPStreamsSDK.initialize(TPStreamsSDK.Provider.TestPress, "lmsdemo")
        Log.d("TPStreamsModule", "TPStreamsSDK initialized")

        // Add a lifecycle listener to listen for activity state changes
        reactContext.addLifecycleEventListener(object : LifecycleEventListener {
            override fun onHostResume() {
                // This will be called when the activity is resumed, so it's safe to get the context
                Log.d("TPStreamsModule", "Activity resumed. Safe to access context.")
            }

            override fun onHostPause() {
                // Optional: Handle pause if necessary
            }

            override fun onHostDestroy() {
                // Optional: Cleanup if needed
            }
        })
    }

    @ReactMethod
    fun initializePlayer(videoId: String, accessToken: String, callback: Callback) {
        Log.d("TPStreamsModule", "initializePlayer called with videoId: $videoId and accessToken: $accessToken")
        Log.d("TPStreamsModule", "initializePlayer: ${reactApplicationContext}")
        // Delay accessing context until the activity is available
        val context = ThemedReactContext(reactApplicationContext,reactApplicationContext.baseContext)
        if (context != null) {
            playerView = TPStreamPlayerView(context)
            Log.d("TPStreamsModule", "playerView created successfully")

            // Set videoId and accessToken to the player view
            playerView?.videoId = videoId
            playerView?.accessToken = accessToken

            // Optionally, you can initialize the player here if needed
            // playerView?.initializePlayer() // Ensure you call this if needed

            callback.invoke("Player initialized successfully")
            Log.d("TPStreamsModule", "Player initialized successfully")
        } else {
            callback.invoke("Failed to initialize player")
            Log.e("TPStreamsModule", "Failed to obtain context or invalid context")
        }
    }

    @ReactMethod
    fun play(callback: Callback) {
        Log.d("TPStreamsModule", "play called")

        // Ensure playerView is initialized
        if (playerView != null) {
            playerView?.initializePlayer()
            callback.invoke("Player started successfully")
            Log.d("TPStreamsModule", "Player started successfully")
        } else {
            callback.invoke("Player not initialized")
            Log.e("TPStreamsModule", "Player not initialized")
        }
    }
}
