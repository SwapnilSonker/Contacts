package dev.swapnil.contacts.ui.messagesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.swapnil.contacts.databinding.ItemMessagesListBinding
import dev.swapnil.contacts.db.MessageEntity
import dev.swapnil.contacts.db.formattedTime

class MessagesAdapter(
    private val messages: List<MessageEntity>
) : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMessagesListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MessagesAdapter.ViewHolder {
        return ViewHolder(
            ItemMessagesListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MessagesAdapter.ViewHolder,
        position: Int,
    ) {
        with (holder.binding) {
            val message = messages[position]
            textViewContactName.text = message.to
            textViewMessage.text = message.message
            textViewTimestamp.text = message.formattedTime
        }
    }

    override fun getItemCount() = messages.size
}