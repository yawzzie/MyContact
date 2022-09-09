package my.edu.tarc.mycontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.mycontact.model.Contact

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    //create a local version of dataset
    private var contactList= emptyList<Contact>()

    internal fun setContact(contact:List<Contact>){
        contactList=contact
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val textViewPhone: TextView = view.findViewById(R.id.textViewPhone)

        init{
            view.setOnClickListener{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.textViewName.text = contact.name
        holder.textViewPhone.text = contact.phone
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

}