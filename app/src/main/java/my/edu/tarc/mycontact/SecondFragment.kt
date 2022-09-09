package my.edu.tarc.mycontact

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import my.edu.tarc.mycontact.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //Get content from another app component
    //implicit intent
    private val getProfilePic = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri ->
        if(uri!==null){
            binding.imageViewPicture.setImageURI(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        //Retrieve data from preferences
        val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
        if(preferences != null){
            if(preferences.contains(getString(R.string.name))){
                binding.editTextTextName.setText(preferences.getString(getString(R.string.name), ""))
            }
            if(preferences.contains(getString(R.string.phone))){
                binding.editTextPhone.setText(preferences.getString(getString(R.string.phone), ""))
            }
        }

        binding.imageViewPicture.setOnClickListener{
            getProfilePic.launch("image/*")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_save).isVisible = true
        menu.findItem(R.id.action_add).isVisible = false
        menu.findItem(R.id.action_profile).isVisible = false
        menu.findItem(R.id.action_settings).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId){
            R.id.action_save ->{
                //create an instance of Preferences
                val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
                val name = binding.editTextTextName.text.toString()
                val phone = binding.editTextPhone.text.toString()
                if (preferences != null) {
                    with(preferences.edit()){
                        putString(getString(R.string.name), name)
                        putString(getString(R.string.phone), phone)
                        apply()
                    }
                }
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}