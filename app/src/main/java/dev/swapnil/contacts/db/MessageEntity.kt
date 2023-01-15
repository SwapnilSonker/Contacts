package dev.swapnil.contacts.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Locale

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val message: String,
    val to: String,
    val timestamp: Long
)

val MessageEntity.formattedTime: String
    get() {
        return SimpleDateFormat("dd/MM/yy hh:mm aaa", Locale.ENGLISH).format(this.timestamp)
    }