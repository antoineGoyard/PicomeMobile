package fr.picom.picomemobile.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import fr.picom.picomemobile.R
import fr.picom.picomemobile.ui.MainActivity
import fr.picom.picomemobile.utils.SessionManager
import fr.picom.picomemobile.viewmodel.ProfilViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [ProfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilFragment : Fragment() {
    private val viewModel by viewModels<ProfilViewModel>()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var lastNameInput: EditText
    private lateinit var firstNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var siretInput: EditText
    private lateinit var companyInput: EditText
    private lateinit var roadInput: EditText
    private lateinit var postalInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        val lastNameLabel = view?.findViewById<TextView>(R.id.lastName_label)
         lastNameInput = view?.findViewById<EditText>(R.id.LastName_input)!!

        val firstNameLabel = view?.findViewById<TextView>(R.id.firstName_label)
         firstNameInput = view?.findViewById<EditText>(R.id.firstName_input)!!

        val emailLabel = view?.findViewById<TextView>(R.id.email_label)
         emailInput = view?.findViewById<EditText>(R.id.email_input)!!

        val phoneLabel = view?.findViewById<TextView>(R.id.phone_label)
         phoneInput = view?.findViewById<EditText>(R.id.phone_input)!!

        val siretLabel = view?.findViewById<TextView>(R.id.siret_label)
         siretInput = view?.findViewById<EditText>(R.id.siret_input)!!

        val companyLabel = view?.findViewById<TextView>(R.id.company_label)
            companyInput = view?.findViewById<EditText>(R.id.company_input)!!

        val roadLabel = view?.findViewById<TextView>(R.id.road_label)
         roadInput = view?.findViewById<EditText>(R.id.road_input)!!

        val postalLabel = view?.findViewById<TextView>(R.id.postal_label)
         postalInput = view?.findViewById<EditText>(R.id.postal_input)!!

        if (lastNameLabel != null) {
            lastNameLabel.text   = "Nom :"
        }
        if (lastNameInput != null) {
            lastNameInput.setText(SessionManager.getSessionUser()?.lastName)
        }

        if (firstNameLabel != null) {
            firstNameLabel.text = "Prénom :"
        }
        if (firstNameInput != null) {
            firstNameInput.setText(SessionManager.getSessionUser()?.firstName)
        }

        if (emailLabel != null) {
            emailLabel.text = "Adresse email :"
        }
        if (emailInput != null) {
            emailInput.setText(SessionManager.getSessionUser()?.email)
        }

        if (phoneLabel != null) {
            phoneLabel.text = "Numéro de téléphone :"
        }
        if (phoneInput != null) {
            phoneInput.setText(SessionManager.getSessionUser()?.phoneNumber)
        }

        if (siretLabel != null) {
            siretLabel.text = "Numéro SIRET :"
        }
        if (siretInput != null) {
            siretInput.setText(SessionManager.getSessionUser()?.numSiret)
        }

        if (companyLabel != null) {
            companyLabel.text = "Nom de l'entreprise :"
        }
        if (companyInput != null) {
            companyInput.setText(SessionManager.getSessionUser()?.companyName)
        }

        if (roadLabel != null) {
            roadLabel.text = "Nom de rue :"
        }
        if (roadInput != null) {
            roadInput.setText(SessionManager.getSessionUser()?.roadName)
        }

        if (postalLabel != null) {
            postalLabel.text = "Code postal :"
        }
        if (postalInput != null) {
            postalInput.setText(SessionManager.getSessionUser()?.postalCode)
        }

        // Inflate the layout for this fragment

        val button = view.findViewById<Button>(R.id.submit)

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                update()
             // goToHomeFragment()

            }
        })

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun update() {
        val lastName = lastNameInput.text.toString()
        val firstName = firstNameInput.text.toString()
        val email = emailInput.text.toString()
        val phone = phoneInput.text.toString()
        val siret = siretInput.text.toString()
        val company = companyInput.text.toString()
        val road = roadInput.text.toString()
        val postal = postalInput.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
        viewModel.updateUser(lastName, firstName, email, phone, siret, company, road, postal)
    // Do something with the input values, such as update a database or send to a server}
        }
    }

    private fun goToHomeFragment() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_wrapper, HomeFragment())
        transaction.commit()
    }



}