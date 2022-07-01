package com.example.advanced_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.advanced_kotlin.databinding.FragmentAddUserBinding

class AddUserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentAddUserBinding

    private val args: AddUserFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUserBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val user = args.user
        if (user != null) {
            binding.save.text = "update"
            binding.etFirstName.setText(user.firstName)
            binding.etLastName.setText(user.lastName)
            binding.etAge.setText(user.age.toString())
        }
        binding.save.setOnClickListener {
            if (!checkInputs()) {
                showToast("please fill all fields")
            } else {
                if (user == null) {
                    viewModel.insertUser(
                        User(
                            firstName = binding.etFirstName.text.toString().trim(),
                            lastName = binding.etLastName.text.toString().trim(),
                            age = binding.etAge.text.toString().trim().toInt()
                        )
                    )
                showToast("user added :)")
                }else{
                    viewModel.insertUser(
                        User(
                        user.id,
                        firstName = binding.etFirstName.text.toString().trim(),
                        lastName = binding.etLastName.text.toString().trim(),
                        age = binding.etAge.text.toString().trim().toInt()
                    )
                    )
                    showToast("user updated")
                }
                requireActivity().onBackPressed()
            }
        }
        return binding.root
    }

    private fun checkInputs(): Boolean {
        if (binding.etFirstName.text.trim().isEmpty() ||
            (binding.etFirstName.text.trim()
                .isEmpty() || (binding.etAge.text.trim().isEmpty()))
        )
            return false
        return true
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}