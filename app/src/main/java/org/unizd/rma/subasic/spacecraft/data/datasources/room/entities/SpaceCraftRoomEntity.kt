package org.unizd.rma.subasic.spacecraft.data.datasources.room.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftRequestModel
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel

@Entity(tableName = "spacecraft")
data class SpaceCraftRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val ime: String,
    val model: String,
    val brzina: Double,
    val slika: String,
    val tezina: Double,
    val datum: String
)

fun SpaceCraftRoomEntity.toSpaceCraftResponseModel(): SpaceCraftResponseModel {
    return SpaceCraftResponseModel(
        id = id ?: 0,
        ime = ime,
        model = model,
        brzina = brzina,
        slika = Uri.parse(slika),
        tezina = tezina,
        datum = datum
    )
}

fun SpaceCraftRequestModel.toSpaceCraftRoomEntity(): SpaceCraftRoomEntity {
    return SpaceCraftRoomEntity(
        id = id,
        ime = ime,
        model = model,
        brzina = brzina,
        slika = slika,
        tezina = tezina,
        datum = datum
    )
}
