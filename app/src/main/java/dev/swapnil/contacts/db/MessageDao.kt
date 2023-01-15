package dev.swapnil.contacts.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {

    @Insert
    suspend fun addMessage(message: MessageEntity)

    @Query("SELECT * FROM messages")
    fun getAllMessages(): LiveData<List<MessageEntity>>
}