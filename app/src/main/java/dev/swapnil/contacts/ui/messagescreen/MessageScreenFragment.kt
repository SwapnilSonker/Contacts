package dev.swapnil.contacts.ui.messagescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.swapnil.contacts.R
import dev.swapnil.contacts.databinding.FragmentContactListBinding
import dev.swapnil.contacts.databinding.FragmentMessageScreenBinding
import dev.swapnil.contacts.ui.contactlist.ContactsViewModel

@AndroidEntryPoint
class MessageScreenFragment : Fragment() {

      private val viewmodel by activityViewModels<ContactsViewModel>()
      private lateinit var binding: FragmentMessageScreenBinding

      override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
      ): View? {
            binding = FragmentMessageScreenBinding.inflate(inflater, container, false)
            return binding.root
      }

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val rnds = (10000..99999).random()
            binding.EditTextContact.text = "Hi, Your OTP is: ${rnds.toString()}"

            binding.buttonSendMessage2.setOnClickListener { it
            //vonage implementation is to be done here
            }
      }
}