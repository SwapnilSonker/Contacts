package dev.swapnil.contacts.networking

import dev.swapnil.contacts.data.Contact
import dev.swapnil.contacts.data.VonageSmsRequest
import dev.swapnil.contacts.data.VoyageSmsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {

    @GET("contacts_Get")
    suspend fun getContacts(): List<Contact>

    @POST
    suspend fun sendMessage(
        @Url url: String,
        @Body message: VonageSmsRequest
    ): VoyageSmsResponse
}