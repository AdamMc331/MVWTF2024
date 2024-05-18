package com.adammcneilly.mvwtf.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val description: String,
) : Parcelable
