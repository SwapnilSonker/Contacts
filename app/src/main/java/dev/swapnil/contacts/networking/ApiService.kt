package dev.swapnil.contacts.networking

import dev.swapnil.contacts.data.Contact
import retrofit2.http.GET

interface ApiService {

    @GET("contacts_Get")
    suspend fun getContacts(): List<Contact>
}