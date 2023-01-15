package dev.swapnil.contacts.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao : UserDao) {

    val getAllContact: LiveData<List<Contact>> = userDao.getAllContact()

    suspend fun addContact(user: Contact){
        userDao.addContact(user.name + user.lastname)
    }
}