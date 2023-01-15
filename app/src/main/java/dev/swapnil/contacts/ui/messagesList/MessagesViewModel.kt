package dev.swapnil.contacts.ui.messagesList

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.swapnil.contacts.db.MessageDao
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val db: MessageDao
): ViewModel() {
    val messages = db.getAllMessages()
}