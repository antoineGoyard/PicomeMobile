package fr.picom.picomemobile.data.response

import com.google.gson.annotations.SerializedName

data class AreaResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("email")
    var email: String,
    @SerializedName("roles")
    var roles: Array<String>,

    )

