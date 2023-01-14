package dev.swapnil.contacts.data

data class VoyageSmsResponse(
    val messages: List<MessageResponse>,
)

data class MessageResponse(
    val status: String
)