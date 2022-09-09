package my.edu.tarc.mycontact

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import my.edu.tarc.mycontact.databinding.FragmentFirstBinding
import my.edu.tarc.mycontact.viewmodel.ContactViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Enable menu item
        setHasOptionsMenu(true)
        //val menuHost: MenuHost = requireActivity()
        //menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onStart() {
        Log.d("onStart", "First Fragment")
        super.onStart()
    }

    override fun onResume() {
        Log.d("onResume", "First Fragment")
        super.onResume()

        val contactAdapter = ContactAdapter()


            contactViewModel.contactList.observe(viewLifecycleOwner){

            if(it.isEmpty())
            {
                Toast.makeText(context,getString(R.string.no_record),Toast.LENGTH_SHORT).show()
            }else{
                contactAdapter.setContact(it)
            }
        }

        binding.recyclerViewContact.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.recyclerViewContact.adapter = contactAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_profile ->{
                val navController = activity?.findNavController(R.id.nav_host_fragment_content_main)
                navController?.navigate(R.id.action_ContactFragment_to_ProfileFragment)
                return true
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_save).isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}