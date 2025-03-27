package com.example.codechallange

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.codechallange.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val loginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpObservers()
        setUpLoginWithViewModel()
    }

    private fun setUpObservers() {
        loginViewModel.loginResult.observe(this) { isSuccessful ->
            if (isSuccessful) goToSuccessScreen() else showToast("Login Error :(")
        }

        loginViewModel.loading.observe(this) { showLoading ->
            hideLoader(showLoading)
        }
    }

    private fun setUpLoginWithViewModel() {
        binding.btnLogin.setOnClickListener {
            val username = binding.tvUserName.editText?.text.toString()
            val password = binding.tvUserName.editText?.text.toString()

            binding.successContent.visibility = View.GONE
            loginViewModel.login(username, password)
        }
    }

    private fun goToSuccessScreen() {
        binding.successContent.visibility = View.VISIBLE
        binding.contentLogin.visibility = View.GONE
        binding.progressLoaderContent.visibility = View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun hideLoader(showLoader: Boolean) {
        binding.progressLoaderContent.visibility = if (showLoader) View.VISIBLE else View.GONE
        binding.contentLogin.visibility = if (!showLoader) View.VISIBLE else View.GONE
    }
}