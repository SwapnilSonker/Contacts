package dev.swapnil.contacts.networking

import contactNameItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("contacts_get")
    suspend fun getData(): List<contactNameItem>
}