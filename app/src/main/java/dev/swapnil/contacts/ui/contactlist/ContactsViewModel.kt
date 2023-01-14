package dev.swapnil.contacts.ui.contactlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.swapnil.contacts.data.Contact
import dev.swapnil.contacts.networking.ApiService
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val api: ApiService
): ViewModel() {
    val showProgressBar = MutableLiveData(true)
    val showError = MutableLiveData("")

    val contactList = MutableLiveData<List<Contact>>()

    var selectedContact: Contact? = null

    init {
        getContacts()
    }

    private fun getContacts() {
        viewModelScope.launch {
            try {
                api.getContacts().let {
                    contactList.value = it
                }
            } catch (e: HttpException) {
                showError.value = e.message()
            } catch (e: Exception) {
                showError.value = e.message
            } finally {
                showProgressBar.value = false
            }
        }
    }
}