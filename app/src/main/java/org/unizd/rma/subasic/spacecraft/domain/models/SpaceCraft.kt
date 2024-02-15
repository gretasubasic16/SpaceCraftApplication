package org.unizd.rma.subasic.spacecraft.domain.models

import android.net.Uri

data class SpaceCraftResponseModel(
    val id: Int,
    val ime: String,
    val model: String,
    val brzina: Double,
    val slika: Uri,
    val tezina: Double,
    val datum: String
)

data class SpaceCraftRequestModel(
    val id: Int? = null,
    val ime: String,
    val model: String,
    val brzina: Double,
    val slika: String,
    val tezina: Double,
    val datum: String
)
