package fr.picom.picomemobile.data.request

import com.google.gson.annotations.SerializedName
import fr.picom.picomemobile.models.Ad
import fr.picom.picomemobile.models.Role


data class UpdateRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("numSiret")
    val numSiret: String?,
    @SerializedName("companyName")
    val companyName: String?,
    @SerializedName("roadName")
    val roadName: String?,
    @SerializedName("postalCode")
    val postalCode: String?,
    @SerializedName("roles")
    val roles: List<Role>?,
    @SerializedName("adList")
    val adList: List<Ad>?,
    @SerializedName("verified")
    val verified: Boolean?
)