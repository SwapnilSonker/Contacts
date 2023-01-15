package dev.swapnil.contacts.ui.messagesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dev.swapnil.contacts.databinding.FragmentMessagesListBinding

@AndroidEntryPoint
class MessagesListFragment : Fragment() {
    private lateinit var binding: FragmentMessagesListBinding
    private val viewModel by viewModels<MessagesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessagesListBinding.inflate(inflater, container, false)
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

        viewModel.messages.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.textViewEmpty.visibility = View.VISIBLE
            } else {
                binding.listMessages.adapter = MessagesAdapter(it)
            }
        }
    }
}