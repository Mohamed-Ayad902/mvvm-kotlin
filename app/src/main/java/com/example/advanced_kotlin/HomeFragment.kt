package com.example.advanced_kotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advanced_kotlin.databinding.FragmentHomeBinding

private const val TAG = "HomeFragment mohamed"

class HomeFragment : Fragment(), UserAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        setupRecyclerView()

        viewModel.getUsers().observe(requireActivity()) {
            userAdapter.userList = it
            Log.d(TAG, "onCreateView: userList changed => $it")
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addUserFragment)
        }
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            userAdapter = UserAdapter(this@HomeFragment)
            adapter = userAdapter
        }
    }

    override fun onClick(user: User) {
        val action = HomeFragmentDirections.actionHomeFragmentToAddUserFragment(user)
        findNavController().navigate(action)
    }

    override fun onDeleteClick(user: User) {
        viewModel.deleteUser(user)
        Log.e(TAG, "onDeleteClick: deleted")
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}