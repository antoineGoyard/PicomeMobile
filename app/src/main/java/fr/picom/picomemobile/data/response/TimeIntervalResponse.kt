package fr.picom.picomemobile.data.response

import com.google.gson.annotations.SerializedName
import fr.picom.picomemobile.models.Area
import fr.picom.picomemobile.models.TimeInterval

data class TimeIntervalResponse (
    @SerializedName("TimeIntervalList")
    val TimeIntervalList: List<TimeInterval>
)