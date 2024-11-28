package com.testpressreactnativesdk

import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class TPStreamPlayerManager : SimpleViewManager<TPStreamPlayerView>() {

    override fun getName(): String = "TPStreamPlayer"

    override fun createViewInstance(reactContext: ThemedReactContext): TPStreamPlayerView {
        return TPStreamPlayerView(reactContext)
    }

    @ReactProp(name = "videoId")
    fun setVideoId(view: TPStreamPlayerView, videoId: String) {
        view.initializePlayer(videoId, view.accessToken) // Ensure `accessToken` is set before calling.
    }

    @ReactProp(name = "accessToken")
    fun setAccessToken(view: TPStreamPlayerView, accessToken: String) {
        view.accessToken = accessToken
    }
}
