package com.shahriar.task8.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val id: Int,
    val type: String?,
    val name: String?,
    val imageURL: String?,
    val city: String?,
    val country: String?,
    val price: Int?,
    val hour: Int?,
    val temperature: Int?,
    val description: String?,
    val rating: Number?
): Parcelable
