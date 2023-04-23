package fr.picom.picomemobile.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.picom.picomemobile.R
import fr.picom.picomemobile.adapters.AdAdapter
import fr.picom.picomemobile.data.response.BaseResponse
import fr.picom.picomemobile.databinding.ActivityMainBinding
import fr.picom.picomemobile.models.Ad
import fr.picom.picomemobile.ui.fragments.AdFragment
import fr.picom.picomemobile.ui.fragments.HomeFragment
import fr.picom.picomemobile.ui.fragments.ProfilFragment
import fr.picom.picomemobile.utils.SessionManager
import fr.picom.picomemobile.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()

    private val homeFragment = HomeFragment()
    private val profilFragment = ProfilFragment()
    private val adFragment = AdFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //récupération de l'utilisateur
        val cookie = SessionManager.getSavedCookie()
        val id = SessionManager.getSessionUser()?.id
        if (cookie != null && id !=null ) {
            viewModel.getUser(id, cookie)
        }

        setupBottomNavigationView()

        viewModel.userResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    // handle loading state
                }

                is BaseResponse.Success -> {
                    // update session user with data
                    it.data?.let { user -> SessionManager.updateUserFromLogin(user) }
                    makeCurrentFragment(homeFragment)
                }

                is BaseResponse.Error -> {
                    // handle error state
                }

                else -> {
                    // handle other states
                }
            }
        }
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigation.setOnItemSelectedListener{ menuItem ->
            when(menuItem.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_profil -> makeCurrentFragment(profilFragment)
                R.id.ic_new_ad -> makeCurrentFragment(adFragment)
                R.id.ic_logout -> logout()
            }
            true
        }
    }



    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper,fragment)
            addToBackStack(null) // add this line to add the fragment to back stack
            commit()
        }

    private fun logout() {
        SessionManager.clearSession()
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


    override fun onPause() {
        super.onPause()
        if (adFragment.isVisible) {
            supportFragmentManager.beginTransaction().apply {
                addToBackStack(null)
            }.commit()
        }
    }
}