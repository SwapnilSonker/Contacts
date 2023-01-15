package dev.swapnil.contacts.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(user: String): Contact

    @Query("SELECT * FROM contact_table")
    fun getAllContact():LiveData<List<Contact>>

}