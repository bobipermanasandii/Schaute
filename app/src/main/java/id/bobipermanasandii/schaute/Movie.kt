package id.bobipermanasandii.schaute

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val title: String,
    val overview: String,
    val posterPath: String

) : Parcelable
