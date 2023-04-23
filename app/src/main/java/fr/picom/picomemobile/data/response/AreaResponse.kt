package fr.picom.picomemobile.data.response

import com.google.gson.annotations.SerializedName
import fr.picom.picomemobile.models.Ad
import fr.picom.picomemobile.models.Area

data class AreaResponse(
    @SerializedName("areaList")
    val areaList: List<Area>
    )

