package com.example.hw32

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.hw32.databinding.FragmentSignUpBinding

class SignUp : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private var intentUserName = ""
    private var intentPassword = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        init()
        viewModel.loginUserName.observe(viewLifecycleOwner) { username ->
            etUserName.setText(username)
        }
        viewModel.loginPassword.observe(viewLifecycleOwner) { password ->
            etPassword.setText(password)
        }

        binding.btnSignUpSecond.setOnClickListener {
            passData()
        }
        return binding.root
    }

    private fun init() {
        etUserName = binding.inputUsername
        etPassword = binding.inputPassword
        intentUserName = viewModel.loginUserName.value.toString()
        intentPassword = viewModel.loginPassword.value.toString()
        etUserName.setText(intentUserName)
        etPassword.setText(intentPassword)
    }

    private fun passData() {
        val username = etUserName.text.toString()
        val password = etPassword.text.toString()
        if (username == "" || password == "") Toast.makeText(
            context,
            "Please type your information",
            Toast.LENGTH_SHORT
        ).show()
        else {
            viewModel.setSignUpUserName(etUserName.text.toString())
            viewModel.setSignUpPassword(etPassword.text.toString())
            binding.root.findNavController().navigate(R.id.action_signUp_to_login)
        }
    }
}