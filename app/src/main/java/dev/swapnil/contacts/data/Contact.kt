package dev.swapnil.contacts.data

data class Contact(
    val lastname: String,
    val name: String,
    val phone: String
)

val Contact.fullName: String
    get() = "$name $lastname"