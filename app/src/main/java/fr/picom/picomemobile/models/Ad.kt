package fr.picom.picomemobile.models

import java.util.Date

data class Ad(
    val id: Int,
    val title: String,
    val image: String,
    val createdAt: Date,
    val StartAt: Date,
    val numDaysOfDiffusion: Int
)