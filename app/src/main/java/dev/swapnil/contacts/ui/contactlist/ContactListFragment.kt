package dev.swapnil.contacts.ui.contactlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.swapnil.contacts.R
import dev.swapnil.contacts.databinding.FragmentContactListBinding

@AndroidEntryPoint
class ContactListFragment : Fragment() {
    private lateinit var binding: FragmentContactListBinding

    private val viewModel by activityViewModels<ContactsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
                viewModel.showError.value = ""
            }
        }

        viewModel.contactList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.listContacts.adapter = ContactsAdapter(it) {
                    viewModel.selectedContact = it
                    findNavController().navigate(R.id.action_contactListFragment_to_contactDetailFragment)
                }
            }
        }
    }
}