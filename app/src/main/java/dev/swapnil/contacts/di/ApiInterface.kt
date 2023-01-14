package dev.swapnil.contacts.di

import contactNameItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("contacts_Get")
    fun getData(): Call<List<contactNameItem>>
}