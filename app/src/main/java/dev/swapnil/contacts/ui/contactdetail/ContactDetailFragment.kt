package dev.swapnil.contacts.ui.contactdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.swapnil.contacts.R
import dev.swapnil.contacts.databinding.FragmentContactDetailBinding
import dev.swapnil.contacts.ui.contactlist.ContactsViewModel

@AndroidEntryPoint
class ContactDetailFragment : Fragment() {

    private val viewModel by activityViewModels<ContactsViewModel>()
    private lateinit var binding: FragmentContactDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )

        binding.textViewContact.text = "${viewModel.selectedContact?.name} ${viewModel.selectedContact?.lastname}"
        binding.textViewPhone.text = viewModel.selectedContact?.phone

        binding.buttonSendMessage.setOnClickListener {
            // Move to next screen
            findNavController().navigate(R.id.action_contactDetailFragment_to_messageScreenFragment)
        }
    }
}