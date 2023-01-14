package dev.swapnil.contacts.data

import dev.swapnil.contacts.BuildConfig

data class VonageSmsRequest(
    val from: String,
    val text: String,
    val to: String,
    val api_key: String = BuildConfig.VONAGE_API_KEY,
    val api_secret: String = BuildConfig.VONAGE_API_SECRET
)
