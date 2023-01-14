package dev.swapnil.contacts.networking

import contactNameItem
import retrofit2.http.GET

interface ApiService {

    @GET("contacts_get")
    suspend fun getData(): List<contactNameItem>
}