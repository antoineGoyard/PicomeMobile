package fr.picom.picomemobile.models

data class User(
    val id: Int,
    val lastName: String?,
    val firstName: String?,
    val email: String,
    val password: String?,
    val phoneNumber: String?,
    val numSiret: String?,
    val companyName: String?,
    val roadName: String?,
    val postalCode: String?,
    val city: Int,
    val roles: List<Role>?,
    val adList: List<Ad>?,
    val verified: Boolean?
){
    constructor(id: Int, email: String) : this(
        id = id,
        email = email,
        lastName = null,
        firstName = null,
        password = null,
        phoneNumber = null,
        numSiret = null,
        companyName = null,
        roadName = null,
        postalCode = null,
        roles = null,
        adList = null,
        verified = null,
        city = 4
    )

}
