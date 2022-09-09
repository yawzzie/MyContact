package my.edu.tarc.mycontact.repository

import androidx.lifecycle.LiveData
import my.edu.tarc.mycontact.dao.ContactDao
import my.edu.tarc.mycontact.model.Contact

class ContactRepository (private val contactDao: ContactDao) {
    //Cache copy of dataset
    val allContact: LiveData<List<Contact>> = contactDao.getAll()

    suspend fun insert(contact: Contact){
        contactDao.insert(contact)
    }

    suspend fun delete(contact: Contact){
        contactDao.delete(contact)
    }

    suspend fun update(contact: Contact){
        contactDao.update(contact)
    }

    fun findByName(name: String): Contact{
        return contactDao.findByName(name)
    }
}