package fr.picom.picomemobile.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import fr.picom.picomemobile.data.response.BaseResponse
import fr.picom.picomemobile.data.response.LoginResponse
import fr.picom.picomemobile.databinding.ActivityLoginBinding
import fr.picom.picomemobile.databinding.ActivityMainBinding
import fr.picom.picomemobile.utils.SessionManager
import fr.picom.picomemobile.viewmodel.LoginViewModel
import retrofit2.Retrofit


class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        SessionManager.initialize(this)

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    stopLoading()
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            doLogin()

        }

        binding.btnRegister.setOnClickListener {
            doSignup()
        }

    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    fun doLogin() {
        val email = binding.txtInputEmail.text.toString()
        val pwd = binding.txtPass.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)
    }

    fun doSignup() {
        val email = binding.txtInputEmail.text.toString()
        val pwd = binding.txtPass.text.toString()

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Adresse email invalide", Toast.LENGTH_SHORT).show()
            return
        }
        if (!isValidPassword(pwd)) {
            Toast.makeText(this, "Le mot de passe doit avoir au moins 5 caractères, une lettre minuscule, une lettre majuscule et un chiffre", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.registerUser(email = email, pwd = pwd)
        Toast.makeText(this, "Nouveau compte crée", Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }

    fun processLogin(data: LoginResponse?) {
        showToast("Connexion")
        navigateToHome()
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}\$".toRegex()
        return passwordPattern.matches(password)
    }
}
