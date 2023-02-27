package com.example.hw32

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.hw32.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class Login : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var usernameEt: TextView
    private lateinit var passwordEt: TextView
    private var userName = ""
    private var password = ""
    private var intentUserName = ""
    private var intentPassword = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        usernameEt = binding.inputUsername
        passwordEt = binding.inputPassword
        viewModel.loginUserName.observe(viewLifecycleOwner) { login ->
            usernameEt.text = login
        }
        viewModel.loginPassword.observe(viewLifecycleOwner) { password ->
            passwordEt.text = password
        }
        viewModel.signUpUserName.observe(viewLifecycleOwner) { login ->
            intentUserName = login
        }
        viewModel.signUpPassword.observe(viewLifecycleOwner) { password ->
            intentPassword = password
        }
        binding.btnLogin.setOnClickListener {
            userName = usernameEt.text.toString()
            password = passwordEt.text.toString()
            if (userName == intentUserName && password == intentPassword && userName.trim() != "" && password.trim() != "") {
                Snackbar.make(binding.root, "Welcome to our community", Snackbar.LENGTH_LONG).show()
            } else if (userName == intentUserName && password != intentPassword && userName.trim() != "" && password.trim() != "") {
                Snackbar.make(binding.root, "password not match!", Snackbar.LENGTH_LONG).show()
            } else if (userName != intentUserName && password == intentPassword && userName.trim() != "" && password.trim() != "") {
                Snackbar.make(binding.root, "username not match!", Snackbar.LENGTH_LONG).show()
            } else if (userName.trim() == "" && password.trim() == "") {
                Toast.makeText(context, "userName & password is empty", Toast.LENGTH_SHORT)
                    .show()
            } else if (userName.trim() == "") {
                Toast.makeText(context, "userName is empty", Toast.LENGTH_SHORT).show()
            } else if (password.trim() == "" && userName.trim() != "") {
                Toast.makeText(context, "password is empty", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    "Login information is not match with signUp information.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.btnSignup.setOnClickListener {
            viewModel.setLoginUserName(usernameEt.text.toString())
            viewModel.setLoginPassword(passwordEt.text.toString())
            binding.root.findNavController().navigate(R.id.action_login_to_signUp)
        }
        return binding.root
    }
}