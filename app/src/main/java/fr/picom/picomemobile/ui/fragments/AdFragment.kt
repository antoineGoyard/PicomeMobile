package fr.picom.picomemobile.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import fr.picom.picomemobile.R
import fr.picom.picomemobile.models.Area
import fr.picom.picomemobile.models.TimeInterval
import fr.picom.picomemobile.utils.SessionManager
import fr.picom.picomemobile.utils.SessionManager.getSessionArea
import fr.picom.picomemobile.utils.SessionManager.getSessionTimeInterval
import fr.picom.picomemobile.viewmodel.AdViewModel
import java.util.jar.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [AdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdFragment : Fragment() {
    // TODO: Rename and change types of parameters

    val cookie = SessionManager.getSavedCookie()
    private val viewModel by viewModels<AdViewModel>()

    private val timeIntervals = listOf(
        "6-7",
        "7-8",
        "8-9",
      "9-10",
        "10-11",
         "11-12",
       "12-13",
        "13-14",
      "14-15",
        "15-16",
        "16-17",
        "17-18",
         "18-19",
       "19-20"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ad, container, false)

        // Configurer l'adaptateur pour le spinner de zone
        val areaList = getSessionArea()
        val areaNames: List<String> = getAreaNames(areaList)
        val areaSpinner = view.findViewById<Spinner>(R.id.areaSpinner)
        val adapterArea =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, areaNames)
        adapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        areaSpinner.adapter = adapterArea
        areaSpinner.adapter = adapterArea

        // Configurer l'adaptateur pour le spinner de créneau horaire
        val timeSlotSpinner = view.findViewById<Spinner>(R.id.slotSpinner)
        val adapterSlot =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, timeIntervals)
        adapterSlot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSlotSpinner.adapter = adapterSlot

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun getAreaNames(areaList: List<Area>): List<String> {
        return areaList.map { area -> area.name }
    }



}