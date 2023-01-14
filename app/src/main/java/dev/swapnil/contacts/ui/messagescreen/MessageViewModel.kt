package dev.swapnil.contacts.ui.messagescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.swapnil.contacts.data.VonageSmsRequest
import dev.swapnil.contacts.networking.ApiService
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val api: ApiService
): ViewModel() {

    val showProgressBar = MutableLiveData(false)
    val showMessage = MutableLiveData("")
    val buttonSendMessageEnabled = MutableLiveData(true)

    fun sendMessage(otp: String, phone: String) {
        showProgressBar.value = true
        buttonSendMessageEnabled.value = false
        val message = VonageSmsRequest(
            from = "Vonage APIs",
            text = "Hi, Your OTP is: $otp",
            to = phone.trimStart('+')
        )
        viewModelScope.launch {
            try {
                val result = api.sendMessage(
                    url = "https://rest.nexmo.com/sms/json",
                    message = message
                )
                if (result.messages[0].status == "0") {
                    showMessage.value = "SMS sent successfully"
                } else {
                    showMessage.value = "SMS sending failed"
                    buttonSendMessageEnabled.value = true
                }
            } catch (e: HttpException) {
                showMessage.value = e.message()
                buttonSendMessageEnabled.value = true
            } catch (e: Exception) {
                showMessage.value = e.message
                buttonSendMessageEnabled.value = true
            } finally {
                showProgressBar.value = false
            }
        }
    }
}