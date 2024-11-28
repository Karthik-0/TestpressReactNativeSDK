package com.testpressreactnativesdk

import android.util.Log
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.testpressreactnativesdk.TPStreamPlayerView
import com.facebook.react.bridge.ReactMethod

class TPStreamPlayerManager : SimpleViewManager<TPStreamPlayerView>() {

    override fun getName(): String = "TPStreamPlayer"

    override fun createViewInstance(reactContext: ThemedReactContext): TPStreamPlayerView {
        return TPStreamPlayerView(reactContext)
    }

    @ReactProp(name = "videoId")
    fun setVideoId(view: TPStreamPlayerView, videoId: String) {
        view.videoId = videoId
    }

    @ReactProp(name = "accessToken")
    fun setAccessToken(view: TPStreamPlayerView, accessToken: String) {
        view.accessToken = accessToken
    }

    @ReactMethod
    fun play(view: TPStreamPlayerView) {
        // Initialize player only when both videoId and accessToken are available
        if (view.videoId.isNotEmpty() && view.accessToken.isNotEmpty()) {
            view.initializePlayer()
        } else {
            Log.e("TPStreamPlayerManager", "Cannot initialize player: videoId or accessToken is missing")
        }
    }
}
