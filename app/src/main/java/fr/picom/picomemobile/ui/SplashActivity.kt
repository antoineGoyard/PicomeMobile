package fr.picom.picomemobile.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.picom.picomemobile.utils.SessionManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SessionManager.initialize(this)
        // Check if the user has already logged in
        val isLoggedIn = checkUserLoggedIn()
        // If the user is already logged in, start the MainActivity
        if (isLoggedIn) {
            startMainActivity()
        } else {

            // Otherwise, start the LoginActivity
            startLoginActivity()
        }

        // Finish the current activity so the user cannot go back to it
        finish()
    }

    private fun checkUserLoggedIn(): Boolean {
        if(SessionManager.isUserLoggedIn()){
            return true
        }
    return false
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}