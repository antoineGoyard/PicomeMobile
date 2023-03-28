package fr.picom.picomemobile.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_main)
        // initialisation fragment

        //récupération de l'utilisateur
        val cookie = SessionManager.getSavedCookie()
        val id = SessionManager.getSessionUser()?.id
        if (cookie != null && id !=null ) {
            viewModel.getUser(id, cookie)
        }

        val homeFragment = HomeFragment()
        val profilFragment = ProfilFragment()
        val adFragment = AdFragment()


        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_profil -> makeCurrentFragment(profilFragment)
                R.id.ic_new_ad -> makeCurrentFragment(adFragment)
                R.id.ic_logout -> logout()
            }
            true
        }

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

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

    private fun logout(){
        SessionManager.clearSession()
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
}
