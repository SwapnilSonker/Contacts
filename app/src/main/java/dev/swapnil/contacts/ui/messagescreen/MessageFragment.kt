package dev.swapnil.contacts.ui.messagescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.swapnil.contacts.R
import dev.swapnil.contacts.databinding.FragmentMessageBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MessageFragment : Fragment() {

    private lateinit var binding: FragmentMessageBinding
    private val navArgs by navArgs<MessageFragmentArgs>()

    private val viewModel by viewModels<MessageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMessageBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        var otp = ""
        repeat(6) {
            otp += (0..9).random()
        }

        binding.textViewMessageBody.text = "Hi, Your OTP is: $otp"

        binding.buttonSendMessage.setOnClickListener {
            viewModel.sendMessage(
                otp,
                navArgs.phone
            )
        }

        viewModel.buttonSendMessageEnabled.observe(viewLifecycleOwner) {
            binding.buttonSendMessage.isEnabled = it
        }

        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.showMessage.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                if (it == "SMS sent successfully") {
                    lifecycleScope.launch {
                        delay(2000)
                        findNavController().navigate(R.id.action_messageScreenFragment_to_contactListFragment)
                    }
                }

                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.showMessage.value = ""
            }
        }
    }
}
