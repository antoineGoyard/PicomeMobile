package fr.picom.picomemobile.data.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("id")
    var id: Int,
    @SerializedName("Cookie")
    var cookie: String,
)