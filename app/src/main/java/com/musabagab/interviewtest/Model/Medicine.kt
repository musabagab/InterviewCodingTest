package com.musabagab.interviewtest.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medicine(
    var name: String,
    var dose: String,
    var strength: String
) : Parcelable