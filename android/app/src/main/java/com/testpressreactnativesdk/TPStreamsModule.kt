package com.testpressreactnativesdk

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.tpstream.player.TPStreamsSDK

class TPStreamsModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String = "TPStreams"

    @ReactMethod
    fun initialize(provider: String, orgCode: String) {
        TPStreamsSDK.initialize(provider, orgCode)
    }
}