package com.eighthours.sample.config

import com.google.gson.GsonBuilder

internal fun gsonConfiguration(): GsonBuilder.() -> Unit = {
    setPrettyPrinting()
}
