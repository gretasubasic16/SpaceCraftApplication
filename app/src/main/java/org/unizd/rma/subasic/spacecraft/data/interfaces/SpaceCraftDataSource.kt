package org.unizd.rma.subasic.spacecraft.data.interfaces


import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftRequestModel
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel

interface SpaceCraftDataSource {
    suspend fun getAll(): List<SpaceCraftResponseModel>

    suspend fun getOne(id: Int): SpaceCraftResponseModel?

    suspend fun delete(id: Int)

    suspend fun update(
        id: Int,
        ime: String,
        model: String,
        brzina: Double,
        slika: String,
        tezina: Double,
        datum: String
    )

    suspend fun create(data: SpaceCraftRequestModel)
}
