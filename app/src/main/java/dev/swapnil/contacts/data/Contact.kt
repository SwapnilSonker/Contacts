package dev.swapnil.contacts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    val lastname: String,
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val phone: String
)