package dev.swapnil.contacts.ui.contactlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.swapnil.contacts.data.Contact
import dev.swapnil.contacts.databinding.ItemContactsListBinding

class ContactsAdapter(
    private val contacts: List<Contact>,
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemContactsListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ContactsAdapter.ViewHolder {
        return ViewHolder(
            ItemContactsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ContactsAdapter.ViewHolder,
        position: Int,
    ) {
        val contact = contacts[position]
        with (holder.binding) {
            textViewContact.text = "${contact.name} ${contact.lastname}"
            root.setOnClickListener {
                onClick(contact)
            }
        }
    }

    override fun getItemCount() = contacts.size
}